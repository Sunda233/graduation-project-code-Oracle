package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.service.CaregiverService;
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
 * 对看护人员操作
 */
@Slf4j
@Controller
public class CaregiverController {
    @Autowired
    CaregiverService caregiverService;

    RspMessage rspMessage = new RspMessage();


    /**
     * 查看所有看护人员并转向信息页面信息
     * @param
     * @return 看护人员界面
     */
    @GetMapping(value = {"/table_caregiverinfo","/table_caregiverinfo.html"})
    public String toMain(Model model){
        System.out.println("###############################################");
        System.out.println("查看看护人员界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspCaregiverinfo> info=caregiverService.getAllInfo();
        //传向前端，将数据放进model
        model.addAttribute("modelEspCaregiverinfo",info);
        return "table/table_caregiverinfo";
    }

/**
     * 查询所有看护人员信息(ttt)
     * @return list数组
     */
    @ResponseBody
    @GetMapping(value = {"/getAllinfo/Caregiver"})
    public List<EspCaregiverinfo> getinfoCaregiver(){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("获取看护人员所有数据");
        System.out.println("###############################################");
        //查出亲属信息
        caregiverService.getAllInfo();
        //输出看护人员信息
        System.out.println( caregiverService.getAllInfo());
        return caregiverService.getAllInfo();
    }

    /**
     *删除看护人员信息
     * @param no
     * @return
     */
    @ResponseBody
    @PostMapping("/caregiver/delete")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage delete(@RequestParam("caregiverEmpno") String no){/*重定向，携带参数*/
        log.info("进入删除看护人员操作");
        //no为老人的编号
        //删除功能
        System.out.println(no);
        // oldmanService.removeById(no);
        boolean bool= caregiverService.deleteinfo(no);
        System.out.println( "删除结果"+bool);
        // ra.addAttribute("pn",pn);
        if (bool==true){
            rspMessage.setMsgCode("2000");
            rspMessage.setMessage("操作成功");
        }else {
            rspMessage.setMessage("操作失败");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }
    /**
     *根据组号查询所有看护人员信息（组长查看本组看护人员信息）
     */

    /**
     * 添加一条看护信息(t)
     */
    @ResponseBody
    @PostMapping("/insert/caregiver")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage insert(EspCaregiverinfo espCaregiverinfo){/*重定向，携带参数*/
        //子属性赋值
        //设置ID
        String empno=IndexController.suijishu(3);/*随机数函数*/
        espCaregiverinfo.setCaregiverEmpno(empno);
        System.out.println("传值测试"+espCaregiverinfo);
        //执行添加操作
        boolean bool=  caregiverService.insertinfo(espCaregiverinfo);
        //查看id的返回值
        System.out.println("id"+espCaregiverinfo.getCaregiverId());
        System.out.println( "添加结果"+bool);
        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("恭喜你注册成功");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }

    /**/
    /**
     * 通过编号查询看护人员信息(执行修改操作用)
     */
    @ResponseBody
    @PostMapping("/selectone/caregiver")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public EspCaregiverinfo selectone(@RequestParam("no") String no) {/*重定向，携带参数*/
        //查看前台传来的参数
        log.info("前台传过来的参数为"+no);
        System.out.println("根据编号进入信息查询");
        EspCaregiverinfo info= caregiverService.getOneInfo(no);
        System.out.println("查出来的信息为"+info);
        //信息传到前端
        return info;
    }
    /**
     * 修改看护人员信息
     */
    @ResponseBody
    @PostMapping("/update/caregiver")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspCaregiverinfo info ){/*重定向，携带参数*/
        //进入修改操作
        boolean bool= caregiverService.updateinfo(info);
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
}
