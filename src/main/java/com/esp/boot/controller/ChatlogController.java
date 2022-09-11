package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.*;
import com.esp.boot.service.CaregiverService;
import com.esp.boot.service.ChatlogService;
import com.esp.boot.service.RelativesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class ChatlogController {
    @Autowired
    ChatlogService chatlogService;
    @Autowired
    CaregiverService caregiverService;
    @Autowired
    RelativesService relativesService;

    RspMessage rspMessage = new RspMessage();

    /**
     * 跳转页面--看护人员收件箱
     */
    @GetMapping("/caregiver/email")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String select(Model model, HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);

        EspCaregiverinfo caregiverleader=caregiverService.getOneInfo(no);//根据编号查询看护组长信息
        session.setAttribute("info",caregiverleader);//看护组长信息传至页面
        System.out.println("=======================================================");
        /*根据看护组长信息查出邮件*/
        System.out.println(no);
        List<EspChatlog> chatlogshou = chatlogService.getSomeInfoByCaregiver(no,23);//查出收件箱
        List<EspChatlog> chatlogfa = chatlogService.getSomeInfoByCaregiver(no,32);//查出发件箱
        //当数据库无信息时给手动添加一条
        if (chatlogshou.size()==0){
            EspChatlog chatlog = new EspChatlog();
            //EspCaregiverleaderinfo caregiverleaderinfo = new EspCaregiverleaderinfo();
            EspCaregiverinfo espCaregiverinfo = new EspCaregiverinfo();
            espCaregiverinfo.setCaregiverEmpno("00000");
            EspRelativesinfo espRelativesinfo = new EspRelativesinfo();
            espRelativesinfo.setRelativesSerialnumber("00000");
            chatlog.setCaregiverinfo(caregiverleader);//收件人发件人
            chatlog.setRelativesinfo(espRelativesinfo);
            chatlog.setChatlogSenddate(date());//获取日期
            chatlog.setChatlogSendtime(time());//获取时间
            chatlog.setChatlogSendcontent("当前无信息");//内容
            chatlog.setChatlogId(00000);
            chatlogshou.add(chatlog);
        }//如果没有数据
        EspChatlog oneInfoshou= chatlogshou.stream().findFirst().orElse( null );//获取收件箱第一个元素
        EspChatlog oneInfofa =chatlogfa.stream().findFirst().orElse( null );//获取发件箱第一个元素
        System.out.println("查询测试============"+chatlogshou);//输出查询信息

        System.out.println("----------------------------收件箱"+oneInfoshou+"发件箱"+oneInfofa);
        model.addAttribute("chatlogshou",chatlogshou);
        model.addAttribute("chatlogfa",chatlogfa);
        session.setAttribute("oneInfoshou",oneInfoshou);
        session.setAttribute("oneInfofa",oneInfofa);
        return "email/email_caregiverleader";
    }
    /**
     * 跳转页面--看护人员发件箱
     */
    @GetMapping("/caregiver/email/fajian")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String caregiveremailfajian( Model model, HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);

        EspCaregiverinfo caregiverleader=caregiverService.getOneInfo(no);//根据编号查询看护组长信息
        session.setAttribute("info",caregiverleader);//看护组长信息传至页面
        System.out.println("=======================================================");
        /*根据看护组长信息查出邮件*/
        System.out.println(no);
        List<EspChatlog> chatlogshou = chatlogService.getSomeInfoByCaregiver(no,23);//查出收件箱
        List<EspChatlog> chatlogfa = chatlogService.getSomeInfoByCaregiver(no,32);//查出发件箱

        //当数据库无信息时给手动添加一条
        if (chatlogfa.size()==0){
            EspChatlog chatlog = new EspChatlog();
            //EspCaregiverleaderinfo caregiverleaderinfo = new EspCaregiverleaderinfo();
            EspCaregiverinfo espCaregiverinfo = new EspCaregiverinfo();
            espCaregiverinfo.setCaregiverEmpno("00000");
            EspRelativesinfo espRelativesinfo = new EspRelativesinfo();
            espRelativesinfo.setRelativesSerialnumber("00000");
            chatlog.setCaregiverinfo(caregiverleader);//收件人发件人
            chatlog.setRelativesinfo(espRelativesinfo);
            chatlog.setChatlogSenddate(date());//获取日期
            chatlog.setChatlogSendtime(time());//获取时间
            chatlog.setChatlogSendcontent("当前无信息");//内容
            chatlog.setChatlogId(00000);
            chatlogfa.add(chatlog);
        }//如果没有数据


        EspChatlog oneInfoshou= chatlogshou.stream().findFirst().orElse( null );//获取收件箱第一个元素
        EspChatlog oneInfofa =chatlogfa.stream().findFirst().orElse( null );//获取发件箱第一个元素
        System.out.println("查询测试============"+chatlogshou);//输出查询信息

        System.out.println("----------------------------收件箱"+oneInfoshou+"发件箱"+oneInfofa);


        model.addAttribute("chatlogshou",chatlogshou);
        model.addAttribute("chatlogfa",chatlogfa);
        session.setAttribute("oneInfoshou",oneInfoshou);
        session.setAttribute("oneInfofa",oneInfofa);

        return "email/email_caregiverleader_fa";
    }

    /**
     * 跳转页面--亲属收件箱
     */
    @GetMapping("/relatives/email/shoujian")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String relativesremailshoujian(Model model, HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        EspRelativesinfo relativesinfo=relativesService.getOneInfo(no);//根据编号查询信息
        session.setAttribute("info",relativesinfo);//看护组长信息传至页面
        System.out.println("=======================================================");

        /*根据亲属信息查出邮件*/
        System.out.println(no);
        List<EspChatlog> chatlogshou = chatlogService.getSomeInfoByRelatives(no,32);//查出收件箱
        List<EspChatlog> chatlogfa = chatlogService.getSomeInfoByRelatives(no,23);//查出发件箱
       // System.out.println("查询测试============"+chatlogshou);//输出查询信息
        //当数据库无信息时给手动添加一条
        if (chatlogshou.size()==0){
            EspChatlog chatlog = new EspChatlog();
            //EspCaregiverleaderinfo caregiverleaderinfo = new EspCaregiverleaderinfo();
            EspCaregiverinfo espCaregiverinfo = new EspCaregiverinfo();
            espCaregiverinfo.setCaregiverEmpno("00000");
            EspRelativesinfo espRelativesinfo = new EspRelativesinfo();
            espRelativesinfo.setRelativesSerialnumber("00000");
            chatlog.setCaregiverinfo(espCaregiverinfo);//收件人发件人
            chatlog.setRelativesinfo(relativesinfo);
            chatlog.setChatlogSenddate(date());//获取日期
            chatlog.setChatlogSendtime(time());//获取时间
            chatlog.setChatlogSendcontent("当前无信息");//内容
            chatlog.setChatlogId(00000);
            chatlogshou.add(chatlog);
        }//如果没有数据

        EspChatlog oneInfoshou= chatlogshou.stream().findFirst().orElse( null );//获取收件箱第一个元素
        EspChatlog oneInfofa =chatlogfa.stream().findFirst().orElse( null );//获取发件箱第一个元素
        System.out.println("----------------------------收件箱"+oneInfoshou+"发件箱"+oneInfofa);

        model.addAttribute("chatlogshou",chatlogshou);
        model.addAttribute("chatlogfa",chatlogfa);
        session.setAttribute("oneInfoshou",oneInfoshou);
        session.setAttribute("oneInfofa",oneInfofa);


        return "email/email_relatives_shou";
    }
    /**
     * 跳转页面--亲属发件箱
     */
    @GetMapping("/relatives/email/fajian")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String relativesremailfajian( Model model, HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        EspRelativesinfo relativesinfo=relativesService.getOneInfo(no);//根据编号查询信息
        session.setAttribute("info",relativesinfo);//看护组长信息传至页面
        System.out.println("=======================================================");
        /*根据亲属信息查出邮件*/
        System.out.println(no);
        List<EspChatlog> chatlogshou = chatlogService.getSomeInfoByRelatives(no,32);//查出收件箱
        List<EspChatlog> chatlogfa = chatlogService.getSomeInfoByRelatives(no,23);//查出发件箱

        //当数据库无信息时给手动添加一条
        if (chatlogfa.size()==0){
            EspChatlog chatlog = new EspChatlog();
            //EspCaregiverleaderinfo caregiverleaderinfo = new EspCaregiverleaderinfo();
            EspCaregiverinfo espCaregiverinfo = new EspCaregiverinfo();
            espCaregiverinfo.setCaregiverEmpno("00000");
            EspRelativesinfo espRelativesinfo = new EspRelativesinfo();
            espRelativesinfo.setRelativesSerialnumber("00000");
            chatlog.setCaregiverinfo(espCaregiverinfo);//收件人发件人
            chatlog.setRelativesinfo(relativesinfo);
            chatlog.setChatlogSenddate(date());//获取日期
            chatlog.setChatlogSendtime(time());//获取时间
            chatlog.setChatlogSendcontent("当前无信息");//内容
            chatlog.setChatlogId(00000);
            chatlogfa.add(chatlog);
        }//如果没有数据

        EspChatlog oneInfoshou= chatlogshou.stream().findFirst().orElse( null );//获取收件箱第一个元素
        EspChatlog oneInfofa =chatlogfa.stream().findFirst().orElse( null );//获取发件箱第一个元素
        System.out.println("查询测试============"+chatlogshou);//输出查询信息

        System.out.println("----------------------------收件箱"+oneInfoshou+"发件箱"+oneInfofa);
        model.addAttribute("chatlogshou",chatlogshou);
        model.addAttribute("chatlogfa",chatlogfa);
        session.setAttribute("oneInfoshou",oneInfoshou);
        session.setAttribute("oneInfofa",oneInfofa);

        return "email/email_relatives_fa";
    }
    /**
     * 通过ID查询聊天信息
     */
    @ResponseBody
    @PostMapping("/selectone/chatlog/byid")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public EspChatlog selectone(@RequestParam("chatlogId") Integer chatlogId) {/*重定向，携带参数*/
        //查看前台传来的参数
        //log.info("前台传过来的参数为"+no);
       // System.out.println("根据编号进入信息查询");
        EspChatlog info= chatlogService.getOneInfoByID(chatlogId);
        System.out.println("查出来的信息为"+info);
        //信息传到前端
        return info;
    }
    /**
     * 添加新站内信
     * 看护to亲属
     */
    @ResponseBody
    @PostMapping("/insert/chatlog")
    public RspMessage insert(EspChatlog info, EspCaregiverinfo espCaregiverinfo, EspRelativesinfo relativesinfo){
        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
        info.setRelativesinfo(relativesinfo);
        info.setChatlogState(1);
        info.setChatlogSenddate(date());//获取日期
        info.setChatlogSendtime(time());//获取时间
         System.out.println("传值测试------------------"+info);
        //执行添加操作
        boolean bool=  chatlogService.insertinfo(info);
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
     * 删除私信
     */
    @ResponseBody
    @PostMapping("/delete/chatlog/caregiver")
    public RspMessage delete(@RequestParam("chatlogId") Integer chatlogId,@RequestParam("chatlogState") Integer chatlogState){
        boolean bool=false;
        //执行删除操作
        EspChatlog chatlog =chatlogService.getOneInfoByID(chatlogId);
        if (chatlog.getChatlogState()==1){//原始状态
            if (chatlogState==2){//看护人员要删除
                System.out.println("看护人员要删除");
                bool =  chatlogService.deleteinfoByCaregiver(chatlogId);
            }
            if (chatlogState==3){
                System.out.println("亲属要删除");
                bool=  chatlogService.deleteinfoByRelatives(chatlogId);
            }
        }else if (chatlog.getChatlogState()==2){//看护人员已删除
            if (chatlogState==3){//亲属要删除
                System.out.println("真正删除");
                bool =  chatlogService.deleteinfo(chatlogId);
             }
            } else if (chatlog.getChatlogState()==3){//亲属已删除
            if (chatlogState==2){//看护人员要删除
                System.out.println("真正删除");
                bool =  chatlogService.deleteinfo(chatlogId);
            }
        }
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
     * 获取日期与时间
     * @return
     */
    public String date(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }
    public String time(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String date=df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }
}
