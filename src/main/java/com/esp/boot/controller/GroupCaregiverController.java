package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspGroupInfo;
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

@Slf4j
@Controller
public class GroupCaregiverController {

    @Autowired
    GroupCaregiverService groupCaregiverService;
    GroupService groupService;

    RspMessage rspMessage = new RspMessage();

    /**
     * 跳转到管理小组成员界面
     */
    @GetMapping(value = {"/totable_GroupCaregiver","/totable_GroupCaregiver.html"})
    public String toMain(List<EspCaregivergroup> espCaregivergroup,Model model){
        model.addAttribute("modelinfo",espCaregivergroup);
        /*从数据库中查出信息进行展示*/
        return "table/table_GroupCaregiver";
    }
    /**
     * 查询所有信息（所有组的所有看护人员）
     */
    @GetMapping(value = {"/table_GroupCaregiver","/table_GroupCaregiver.html"})
    public String selectAll(Model model){
        System.out.println("###############################################");
        System.out.println("查询所有信息");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspCaregivergroup> info=groupCaregiverService.getAllInfo();
        //传向前端，将数据放进model
        //System.out.println(info.get(1).getGroupName());
        model.addAttribute("modelinfo",info);
        return  toMain(info,model);
    }

    /**
     * 查询尚未分组的看护信息
     */
    @ResponseBody
    @GetMapping("/select/getSomeInfoByUnGroup")
    public List<EspCaregivergroup> getSomeInfoByUnGroup(){
        //查询尚未分组的组员信息
        List<EspCaregivergroup> info=  groupCaregiverService.getSomeInfoByUnGroup();
        return info;
    }

    /**
     * 看护人员分配小组
     * 增加一条信息
     */
    @ResponseBody
    @PostMapping("/insert/CaregiverGroup")
    public RspMessage insert(EspCaregivergroup info,EspCaregiverinfo espCaregiverinfo){
        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
        //执行添加操作
        boolean bool=  groupCaregiverService.insertinfo(info);
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
     * 删除一条分组信息
     */
    @ResponseBody
    @PostMapping("/delete/CaregiverGroup")
    public RspMessage delete(@RequestParam("no") String no){
        //执行删除操作
        boolean bool=  groupCaregiverService.deleteinfo(no);
        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("操作成功");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }

    /**
     * 修改看护人员进组信息
     */
    @ResponseBody
    @PostMapping("/update/CaregiverGroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspCaregivergroup info ,EspCaregiverinfo espCaregiverinfo){/*重定向，携带参数*/

        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
        log.info("查看传来的值"+info);
        //进入修改操作
        boolean bool= groupCaregiverService.updateinfo(info);
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



 /*   @ResponseBody
    @GetMapping("/select/getSomeInfoGroup")
    public List<EspGroupInfo> getSomeInfoGroup(){
        //查询尚未分组的组员信息
        List<EspGroupInfo> info=groupService.getAllInfo();
        System.out.println(info);
        return info;
    }*/





    /**
     * 根据组号查询组成员信息()
     * 不好使，跳转页面以后js失效
     */
 /*   @PostMapping(value = {"/getSomeinfo/CaregiverByGroupId","/getSomeinfo/CaregiverByGroupId.html"})
    public String select(Integer no, Model model){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去根据组号查询信息界面跳转");
        System.out.println("###############################################");
        *//*从数据库中查出老人信息进行展示*//*
        List<EspCaregivergroup> listinfo=groupCaregiverService.getAllInfoById(no);
        //传向前端，将数据放进model
        model.addAttribute("modelinfo",listinfo);
        System.out.println("查询"+listinfo);
      //  return "redirect:/table_GroupCaregiver";
        return  toMain(listinfo,model);
    }*/
}
