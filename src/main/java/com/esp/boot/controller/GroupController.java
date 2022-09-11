package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.service.GroupCaregiverService;
import com.esp.boot.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 对组信息的操作
 */
@Slf4j
@Controller
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupCaregiverService groupCaregiverService;

    RspMessage rspMessage = new RspMessage();
    /**
     * 跳转到组信息页面，并查询所有组信息
     */
    @GetMapping(value = {"/table_groupinfo","/table_groupinfo.html"})
    public String toMain(Model model){
        System.out.println("###############################################");
        System.out.println("查看小组信息界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspGroupInfo> info=groupService.getAllInfo();
        //传向前端，将数据放进model
        //System.out.println(info.get(1).getGroupName());
        model.addAttribute("modelinfo",info);
        return "table/table_groupinfo";
    }
    /**
     * 查询尚未分组的组员信息
     */
    @ResponseBody
    @GetMapping("/select/getSomeInfoGroup")
    public List<EspGroupInfo> getSomeInfoGroup(){
        //查询尚未分组的组员信息
        List<EspGroupInfo> info=groupService.getAllInfo();
        return info;
    }
    /**
     * 添加一个新的分组
     */
    @ResponseBody
    @PostMapping("/insert/groupinfo")
    public RspMessage insert(EspGroupInfo info,EspCaregiverinfo espCaregiverinfo){
        //子属性赋值
        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
       // System.out.println("传值测试"+info);
        //执行添加操作
        boolean bool=  groupService.insertinfo(info);
        //查看id的返回值
       // System.out.println("id"+info.getGroupId());
      //  System.out.println( "添加结果"+bool);
        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("添加成功");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }
    /**
     * 根据编号查询分组的信息，是小组本身信息，而非组员信息
     */
    @ResponseBody
    @PostMapping("/selectone/groupinfo")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public EspGroupInfo selectone(@RequestParam("no") String no) {/*重定向，携带参数*/
        //查看前台传来的参数
        log.info("前台传过来的参数为"+no);
        System.out.println("根据编号进入信息查询");
        EspGroupInfo info= groupService.getOneInfo(no);
        System.out.println("查出来的信息为"+info);
        //信息传到前端
        return info;
    }
    /**
     * 修改分组信息
     */
    @ResponseBody
    @PostMapping("/update/group")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspGroupInfo info ,EspCaregiverinfo espCaregiverinfo){/*重定向，携带参数*/

        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
        log.info("查看传来的值"+info);
        //进入修改操作
        boolean bool= groupService.updateinfo(info);
        System.out.println( "修改结果"+bool);
        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("修改成功");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }

    /**
     * 删除分组，首先验证小组成员是否为空
     * @param no
     * @return
     */
    @ResponseBody
    @PostMapping("/group/delete")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage delete(@RequestParam("groupId")Integer no){/*重定向，携带参数*/
        log.info("进入删除分组操作");
        System.out.println(no);
        //查询分组中是否还有成员
        List<EspCaregivergroup> listinfo=groupCaregiverService.getAllInfoById(no);
        System.out.println("查看查找结果"+listinfo);
        System.out.println("查看查找结果"+listinfo.size());
       if (listinfo==null||listinfo.size()==0){
           System.out.println("小组人员为空可以删除");
           boolean bool= groupService.deleteinfo(no);
           System.out.println( "删除结果"+bool);
           if (bool==true){
               rspMessage.setMsgCode("2000");
               rspMessage.setMessage("操作成功");
           }else {
               rspMessage.setMessage("操作失败");
               rspMessage.setMsgCode("2001");
           }
       }else{
           rspMessage.setMsgCode("2003");
           rspMessage.setMessage("小组成员不为空删除失败");
       }
        return rspMessage;
    }
}
