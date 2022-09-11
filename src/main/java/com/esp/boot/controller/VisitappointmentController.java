package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.*;
import com.esp.boot.service.VisitappointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class VisitappointmentController {
    @Autowired
    VisitappointmentService visitappointmentService;

    RspMessage rspMessage =new RspMessage();

    /**
     * 亲属预约老人
     */
    @ResponseBody
    @PostMapping("/insert/Visitappointment")
    public RspMessage insert(EspVisitappointment info, @RequestParam("relativesno") String relativesno, @RequestParam("oldmanSerialnumber") String oldmanSerialnumber){
        //子属性赋值
        EspRelativesinfo relativesinfo = new EspRelativesinfo();
        relativesinfo.setRelativesSerialnumber(relativesno);
        EspOldmaninfo oldmaninfo= new EspOldmaninfo();
        oldmaninfo.setOldmanSerialnumber(oldmanSerialnumber);
        info.setRelativesinfo(relativesinfo);//设置亲属编号
        info.setOldmaninfo(oldmaninfo);//设置老人编号
        info.setVisitappointmentCreatedate(shijian());//设置当前时间
        info.setVisitappointmentState("未处理");//设置处理状态

        //查看信息是否正确
        System.out.println("---------------------------------------信息"+info);
        //执行添加操作
        boolean bool= visitappointmentService.insertinfo(info); ///groupCaregiverService.insertinfo(info);
        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("预约申请已发送");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return  rspMessage;
    }
    /**
     * 更新处理单状态
     */
    @ResponseBody
    @PostMapping("/update/state/Visitappointment")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage update(EspVisitappointment info){/*重定向，携带参数*/
        log.info("查看传来的值"+info);
        //根据id查询通知单信息
       EspVisitappointment visitappointment= visitappointmentService.getOneInfo(info.getVisitappointmentId());

       boolean flag1=false;
        if(visitappointment.getVisitappointmentState()=="已完成" ||visitappointment.getVisitappointmentState().equals("已完成")){
            rspMessage.setMessage("预约已完成不可操作");
            rspMessage.setMsgCode("2005");
            log.info("-------------------已完成");
            return rspMessage;
        }else if (visitappointment.getVisitappointmentState()=="已拒绝"||visitappointment.getVisitappointmentState().equals("已拒绝")){
            if(info.getVisitappointmentState()=="已完成"||info.getVisitappointmentState().equals("已完成")){
                rspMessage.setMessage("预约被拒绝不可完成");
                rspMessage.setMsgCode("2005");
                log.info("-------------------预约被拒绝不可完成");
                return rspMessage;
            }
        }else if (visitappointment.getVisitappointmentState()=="未处理"||visitappointment.getVisitappointmentState().equals("未处理")){
                if(info.getVisitappointmentState()=="已完成"||info.getVisitappointmentState().equals("已完成")){
                    rspMessage.setMessage("预约未经审核不可完成");
                    rspMessage.setMsgCode("2005");
                    log.info("-------------------预约未经审核不可完成");
                    return rspMessage;
                }else{
                    flag1=true;//可以进行修改操作
                }
            }else {
                flag1=true;//可以进行修改操作
            }

        boolean bool =false;
        //进入修改操作
        if (flag1=true){
             bool= visitappointmentService.updateinfo(info);
            System.out.println( "修改结果"+bool);
        }

        /*如果传递成功*/
        if (bool==true){
            rspMessage.setMessage("更新成功");
            rspMessage.setMsgCode("2000");
        }else {
            rspMessage.setMessage("出现错误");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }
    /**
     * 根据编号删除预约通知单
     */
    @ResponseBody
    @PostMapping("/delete/Visitappointment")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage delete(EspVisitappointment info){/*重定向，携带参数*/
            log.info("进入删除操作");
            boolean bool= visitappointmentService.deleteinfo(info.getVisitappointmentId());
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
     * 获取当前系统时间
     * @return
     */
  /*  @ResponseBody
    @GetMapping("/shijian")*/
    public String shijian(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         String date=df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }
}
