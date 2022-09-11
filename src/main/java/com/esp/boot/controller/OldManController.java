package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.entity.EspRelativesoldmanrelative;
import com.esp.boot.mapper.OldManMapper;
import com.esp.boot.service.OldmanService;
import com.esp.boot.service.RelativesoldmanrelativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对老人信息的操作
 */
@Slf4j
@Controller
public class OldManController {

    @Autowired
    OldManMapper oldManMapper;
    @Autowired
    OldmanService oldmanService;
    @Autowired
    RelativesoldmanrelativeService relativesoldmanrelativeService;

    RspMessage rspMessage = new RspMessage();

    /**
     * 查看所有老人信息
     * @param
     * @return
     */
    @GetMapping(value = {"/table_oldmaninfo","/table_oldmaninfo.html"})
    public String basic_table(Model model){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去查看老人界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出老人信息进行展示*/
        List<EspOldmaninfo> oldmaninfos=oldmanService.getAllInfo();
                // oldmanService.list();
        //传向前端，将数据放进model
        model.addAttribute("oldmaninfos",oldmaninfos);
        oldmanService.getAllInfo();
      //  System.out.println("自动查询"+oldmaninfos);
        return "table/table_oldmaninfo";
    }

    /**
     *删除老人用户
     * @param no
     * @return
     */
    @GetMapping("/oldman/delete/{no}")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String delete( @PathVariable("no") String no){/*重定向，携带参数*/
        /* 分页
        * , @RequestParam(value = "pn",defaultValue = "1") Integer pn,/*获取页码并且设定默认值为1
                          RedirectAttributes ra*/
        //no为老人的编号
        //删除功能
        System.out.println(no);
       // oldmanService.removeById(no);
        boolean bool= oldmanService.deleteinfo(no);
        System.out.println( "删除结果"+bool);
       // ra.addAttribute("pn",pn);
        return "redirect:/table_oldmaninfo";
    }
    /**
     *添加一条老人信息 （数据库可以）
     * @return
     */
    /*@RequestParam("oldmanName") String oldmanName,
                             @RequestParam("oldmanSex") String oldmanSex,
                             @RequestParam("oldmanAge") String oldmanAge,
                             @RequestParam("oldmanHeight") String oldmanHeight,
                             @RequestParam("oldmanIdnumber") String oldmanIdnumber,
                             @RequestParam("oldmanRoomnumber") String oldmanRoomnumber,
                             @RequestParam("caregiverEmpno") String caregiverEmpno,
                             @RequestParam("oldmanCheckintime") String oldmanCheckintime,
                             Model model,
                             HttpSession session */

    @ResponseBody
    @PostMapping("/insert/oldman")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage insert(EspOldmaninfo espOldmaninfo,EspCaregiverinfo espCaregiverinfo){/*重定向，携带参数*/
        //子属性赋值
        espOldmaninfo.setCaregiverinfo(espCaregiverinfo);
        //设置ID
        String Serialnumber=IndexController.suijishu(1);
        espOldmaninfo.setOldmanSerialnumber(Serialnumber);
        System.out.println("传值测试"+espCaregiverinfo);
        System.out.println(espOldmaninfo);//检查要传的值是否正确
        //执行添加操作
        boolean bool=  oldmanService.insertinfo(espOldmaninfo);
        //查看id的返回值
        System.out.println("id"+espOldmaninfo.getOldmanId());
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

    /**
     * 通过编号查询老人信息
     */
    @ResponseBody
    @PostMapping("/selectone/oldman")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public EspOldmaninfo selectoneOldman(String oldmanSerialnumber) {/*重定向，携带参数*/
        //查看前台传来的参数
        log.info(oldmanSerialnumber);
        System.out.println("根据编号进入信息查询");
       EspOldmaninfo espOldmaninfo= oldmanService.getOneInfo(oldmanSerialnumber);
       System.out.println("查出来的信息为"+espOldmaninfo);
       //信息传到前端
        return espOldmaninfo;
    }


    /**
     *修改一条老人信息 （数据库可以）
     * @return
     */
    @ResponseBody
    @PostMapping("/update/oldman")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspOldmaninfo espOldmaninfo, EspCaregiverinfo espCaregiverinfo ){/*重定向，携带参数*/
        System.out.println("******************************进入修改操作****************************");

        //子属性赋值
        espOldmaninfo.setCaregiverinfo(espCaregiverinfo);
        System.out.println("修改值测试"+espOldmaninfo);
        //  oldmanService.insertinfo(espOldmaninfo);
        boolean bool= oldmanService.updateinfo(espOldmaninfo);
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
     * 根据亲属编号查询老人信息
     */



}
