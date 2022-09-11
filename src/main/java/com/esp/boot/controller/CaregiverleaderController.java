package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.*;
import com.esp.boot.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 看护组长操作
 */

@Slf4j
@Controller
public class CaregiverleaderController {

    @Autowired
    CaregiverService caregiverService;

    @Autowired
    ElderlyPhysicalDataService elderlyPhysicalDataService;

    @Autowired
    GroupService groupService;
    @Autowired
    VisitappointmentService visitappointmentService;
    @Autowired
    GroupCaregiverService groupCaregiverService;
    @Autowired
    IndexService indexService;

    RspMessage rspMessage = new RspMessage();
/**
     * 查看所有看护组长信息
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = {"/getAllinfo/Caregiverleader","/getAllinfo/Caregiverleader.html"})
    public List<EspCaregiverleaderinfo> caregiverleaderinfo(Model model){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去查看所有看护组长界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspCaregiverleaderinfo> listinfo=caregiverService.getAllleaderInfo();
        //传向前端，将数据放进model
        model.addAttribute("oldmaninfos",listinfo);
        System.out.println("查询"+listinfo);
        return listinfo;
    }
    /**
     * 获取所有未分组的看护组长信息
     */
    @ResponseBody
    @GetMapping(value ="/getSomeinfo/Caregiverleader/Ungroup")
    public List<EspCaregiverleaderinfo> getSomeinfoCaregiverleader(Model model){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去查看所有未分组的看护组长界面跳转");
        System.out.println("###############################################");
        //从数据库中查出信息进行展示
        List<EspCaregiverleaderinfo> listinfo=caregiverService.getSomeleaderInfoByUnGroup();
        //传向前端，将数据放进model
        model.addAttribute("oldmaninfos",listinfo);
        System.out.println("查询"+listinfo);
        return listinfo;
    }



    /**
     * 进入本组老人身体数据编辑页面
     *  no 组长工号，通过此获得组号
     * @param model
     * @param session
     * @return
     */
   /* @GetMapping("/table_relatives_getoldmaninfo/{no}")*//*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*//*
    public  String delete(@PathVariable("no") String no, Model model, HttpSession session){*//*重定向，携带参数*//*

        //通过组长工号获得组号

        List<EspRelativesoldmanrelative> info = relativesoldmanrelativeService.getAllInfoBySn(no);

        System.out.println(info);
        EspRelativesinfo relatives=relativesService.getOneInfo(no);

        //查出老人身体数据
        List<EspElderlyPhysicalData> info=elderlyPhysicalDataService.getSomeInfoByGroup(groupid);//根据组号查询身体数据
        model.addAttribute("modelinfo",info);//将老人身体数据放入前端
        //传看护组长数据

        session.setAttribute("info",relatives);

        // session.setAttribute("info",no);
        return "table/table_relatives_getoldmaninfo";
    }*/

    @GetMapping(value = {"/select/ElderlyPhysicalData","/select/ElderlyPhysicalData.html"})
    public String toMain(Model model,HttpSession session){

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);


        System.out.println("###############################################");
        System.out.println("查看身体数据信息界面跳转--看护组长");
        System.out.println("###############################################");
        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面
        /*从数据库中查出信息进行展示*/
        List<EspElderlyPhysicalData> info=elderlyPhysicalDataService.getAllInfo();
        //传向前端，将数据放进model
       // EspElderlyPhysicalData espElderlyPhysicalData;
        System.out.println(info);
        model.addAttribute("modelinfo",info);

        EspCaregiverinfo caregiverleader=caregiverService.getOneInfo(no);//根据编号查询看护组长信息
        session.setAttribute("info",caregiverleader);//看护组长信息传至页面
        System.out.println("============================================="+caregiverleader);

        return "table/table_caregiver_ElderlyPhysicalData";
    }

    /**
     * 添加看护组长信息
     */
    @ResponseBody
    @PostMapping("/insert/caregiverleader")
    public RspMessage insert(EspCaregiverleaderinfo info,EspCaregiverinfo espCaregiverinfo){
        //子属性赋值
        info.setCaregiverinfo(espCaregiverinfo);
        System.out.println("传值测试"+info);
        //执行添加操作
        boolean bool=  caregiverService.insertinfocaregiverleader(info);
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
     * 撤销组长，删除看护组长信息
     */
    @ResponseBody
    @PostMapping("/delete/caregiverleader")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public RspMessage delete(@RequestParam("caregiverEmpno")String caregiverEmpno){/*重定向，携带参数*/
        //查询组长是否有管理的分组
        /*根据看护组长信息查出组号*/

        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(caregiverEmpno);//获取到组长信息

        System.out.println("查看查找结果"+groupInfo);

        if (groupInfo==null){//||listinfo.size()==0
            System.out.println("管理分组为空可以删除");
            boolean bool= caregiverService.deleteinfocaregiverleader(caregiverEmpno);
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
            rspMessage.setMessage("管理分组不为空删除失败");
        }
        return rspMessage;
    }





    /*==========================================看护组长功能================================================================*/

    /**
     * 根据组号查询本组老人信息,用于点击提交新数据时的模态框
     */
    @ResponseBody
    @PostMapping("/selectallbygroup/oldmaninfo")
    public List<EspElderlyPhysicalData> selectone(@RequestParam("groupId") Integer groupId) {
        //查看前台传来的参数
        log.info("前台传过来的组号参数为"+groupId);
        System.out.println("根据编号进入信息查询");
        List<EspElderlyPhysicalData>  info =elderlyPhysicalDataService.getSomeOldmanInfoByGroup(groupId);
        System.out.println("查出来的信息为"+info);
        //信息传到前端
        return info;
    }
    /**
     * 跳转页面
     * 根据看护组长编号查询本组看护信息
     */
    @GetMapping("/table_caregiver_getcaregiverinfoBygroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_caregiver_getcaregiverinfoBygroup(Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);

        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面

        List<EspCaregivergroup> info = groupCaregiverService.getAllInfoById(groupInfo.getGroupId());//根据组号查出信息
        // ra.addAttribute("pn",pn);
        System.out.println(info);

        model.addAttribute("modelinfo",info);
        // session.setAttribute("info",no);
        return "table/table_caregiver_getcaregiverinfoBygroup";
    }
    /**
     * 跳转页面
     * 根据看护组长编号查询本组老人信息
     */
    @GetMapping("/table_caregiver_getoldmaninfoBygroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_caregiver_getoldmaninfoBygroup(Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);
        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面


        List<EspElderlyPhysicalData> info = elderlyPhysicalDataService.getSomeOldmanInfoByGroup(groupInfo.getGroupId());//查出信息
       // List<EspCaregivergroup> info = groupCaregiverService.getSomeOldmanInfoByGroup(groupInfo.getGroupId());//根据组号查出信息(老人)
        // ra.addAttribute("pn",pn);
        System.out.println(info);

        model.addAttribute("modelinfo",info);
        // session.setAttribute("info",no);
        return "table/table_caregiver_getoldmaninfoBygroup";
    }
    /**
     * 跳转页面
     * 根据看护组长编号查询亲属绑定信息
     * 通过老人信息顺便查出亲属信息
     */
    // @GetMapping("/table_caregiver_getrelativesBygroup/{no}")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    // (@PathVariable("no") String no,
    @GetMapping("/table_caregiver_getrelativesBygroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_caregiver_getrelativesBygroup(Model model,HttpSession session){/*重定向，携带参数*/
        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);

        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面

        List<EspElderlyPhysicalData> info = elderlyPhysicalDataService.getAllInfoByGroup(groupInfo.getGroupId());//根据组号查出信息
        System.out.println("信息查询测试------------"+info);

        model.addAttribute("modelinfo",info);
        return "table/table_caregiver_getrelativesBygroup";
    }

    /**
     * 通过组号查询预约通知单
     */

    @GetMapping("/caregiver/table_relatives_Visitappointment")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String select(Model model, HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);

        System.out.println("=======================================================");
        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面

        List<EspVisitappointment> info = visitappointmentService.getSomeInfoByCaregiver( groupInfo.getGroupId());//根据获取的组号查询信息
        System.out.println("查询测试============"+info);//输出查询信息
        model.addAttribute("modelinfo",info);

        EspCaregiverinfo caregiverleader=caregiverService.getOneInfo(no);//根据编号查询看护组长信息
        session.setAttribute("info",caregiverleader);//看护组长信息传至页面
        return "table/table_caregiver_Visitappointment";
    }
    /**
     * 跳转页面
     * 根据看护组长编号查询个人信息
     */
    @GetMapping("/infoOneself_caregiver")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String infoOneself_caregiver(Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("username");
        System.out.println("session获取值测试-------------------------------"+no);


        /*根据看护组长信息查出组号*/
        EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面

        //仅仅是看护人员信息
        EspCaregiverinfo caregiverleader=caregiverService.getOneInfo(no);//根据编号查询看护组长信息
        session.setAttribute("info",caregiverleader);//看护组长信息传至页面

        //查出看护组长的信息
        EspCaregiverleaderinfo caregiverleaderinfo = caregiverService.getoneleaderInfo(no);
        // ra.addAttribute("pn",pn);
         System.out.println(caregiverleaderinfo);
         model.addAttribute("modelinfo",caregiverleaderinfo);
         session.setAttribute("caregiverleaderinfo",caregiverleaderinfo);
        System.out.println("-----------------------------------------------"+caregiverleaderinfo+groupInfo);
        return "infooneself/infoOneself_caregiver";
    }

    /**
     * 看护组长修改个人密码
     */
    @ResponseBody
    @PostMapping("/update/caregiverleaderpwd")
    public RspMessage caregiverleaderpwd(@RequestParam("no") String no,@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd){
        //查询旧密码是否正确，如果正确修改密码，如果错误不修改
        //查询旧密码
        EspCaregiverleaderinfo caregiverleaderinfo = caregiverService.getoneleaderInfo(no);
        if (caregiverleaderinfo.getCareLeaderInfoPassword().equals(oldpwd)){
            //修改密码
            //执行操作
            caregiverleaderinfo.setCareLeaderInfoPassword(newpwd);
            boolean bool=  caregiverService.updateinfocaregiverleader(caregiverleaderinfo);
            if (bool==true){
                rspMessage.setMessage("修改成功");
                rspMessage.setMsgCode("2000");
            }else {
                rspMessage.setMessage("出现错误");
                rspMessage.setMsgCode("2001");
            }
        }else {
            rspMessage.setMessage("密码错误，请重新验证");
            rspMessage.setMsgCode("2001");
        }
        return rspMessage;
    }
}
