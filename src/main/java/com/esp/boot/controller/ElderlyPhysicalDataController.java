package com.esp.boot.controller;


/**
 * 对身体数据表的操作
 */
import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.service.ElderlyPhysicalDataService;
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
public class ElderlyPhysicalDataController {
    @Autowired
    ElderlyPhysicalDataService elderlyPhysicalDataService;

    RspMessage rspMessage = new RspMessage();
    /**
     * 跳转身体数据页面(管理员权限查询所有)
     * 看护组长权限在看护组长controller
     */
    @GetMapping(value = {"/table_ElderlyPhySicalData","/table_ElderlyPhySicalData.html"})
    public String toMain(Model model){
        System.out.println("###############################################");
        System.out.println("查看身体数据信息界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspElderlyPhysicalData> info=elderlyPhysicalDataService.getAllInfo();
        //传向前端，将数据放进model
        EspElderlyPhysicalData espElderlyPhysicalData;
        System.out.println(info);
        model.addAttribute("modelinfo",info);
        return "table/table_ElderlyPhysicalData";
    }

    /**
     * 查询所有的身体数据表（按照看护分）
     */
    @ResponseBody
    @GetMapping("/select/All/ElderlyPhysicalData")
    public List<EspElderlyPhysicalData> getAllInfo(){
        //查询尚未分组的组员信息
        List<EspElderlyPhysicalData> info=elderlyPhysicalDataService.getAllInfo();
        return info;
    }

    /**
     * 添加身体数据表(看护组长)
     */
    @ResponseBody
    @PostMapping("/insert/info/ElderlyPhysicalData")
    public RspMessage insert(EspElderlyPhysicalData info, @RequestParam("oldmanno")String no){
        //子属性赋值
        EspOldmaninfo espOldmaninfo =new EspOldmaninfo();
        espOldmaninfo.setOldmanSerialnumber(no);
        info.setOldmanInfo(espOldmaninfo);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("传值测试"+info);
        //执行添加操作
        boolean bool= elderlyPhysicalDataService.insertinfo(info);
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
     * 删除一条身体数据信息
     */
    @ResponseBody
    @PostMapping("/delete/ElderlyPhysicalData")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage delete(@RequestParam("physicaldataId") Integer no){/*重定向，携带参数*/
        //删除功能
        // oldmanService.removeById(no);
        boolean bool= elderlyPhysicalDataService.deleteinfo(no);
        System.out.println( "删除结果"+bool);
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
     * 修改身体数据信息
     */
    @ResponseBody
    @PostMapping("/update/ElderlyPhysicalData")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspElderlyPhysicalData info , EspOldmaninfo espOldmaninfo){/*重定向，携带参数*/

        //子属性赋值
        info.setOldmanInfo(espOldmaninfo);
        log.info("查看传来的值"+info);
        //进入修改操作
        boolean bool= elderlyPhysicalDataService.updateinfo(info);
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
