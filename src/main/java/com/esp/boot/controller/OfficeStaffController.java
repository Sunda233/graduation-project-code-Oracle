/**
 * 负责管理员端口的页面跳转
 */
package com.esp.boot.controller;

import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.service.CaregiverService;
import com.esp.boot.service.ElderlyPhysicalDataService;
import com.esp.boot.service.GroupCaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OfficeStaffController {
    @Autowired
    CaregiverService caregiverService;
    @Autowired
    ElderlyPhysicalDataService elderlyPhysicalDataService;
    @Autowired
    GroupCaregiverService groupCaregiverService;

    /**
     * 来到消息页
     * @return
     */
    @GetMapping(value = {"/email","/email.html"})//发送此两个跳转到登录页
    public String registerPage(){
        System.out.println("信息界面跳转");
        return "email";
    }

    /**
     *查询所有看护组长信息
     */
    @GetMapping("/table_officestaff_caregiverinfo")
    public String toMain(Model model){
        System.out.println("###############################################");
        System.out.println("查看小组信息界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspCaregiverleaderinfo> info=caregiverService.getAllleaderInfo();//获取所有看护组长信息
        //传向前端，将数据放进model
        //System.out.println(info.get(1).getGroupName());
        model.addAttribute("modelinfo",info);
        return "table/table_officestaff_caregiverinfo";
    }

    /**
     *管理员查询老人亲属绑定信息
     */
    @GetMapping("/table_office_getrelativesBygroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_office_getrelativesBygroup( Model model, HttpSession session){/*重定向，携带参数*/
        /*根据看护组长信息查出组号*/
       /* EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面*/

        List<EspElderlyPhysicalData> info = elderlyPhysicalDataService.getAllInfoByStaff();//查出信息
        System.out.println("信息查询测试------------"+info);

        model.addAttribute("modelinfo",info);
        return "table/table_office_getrelativesBygroup";
    }

    /**
     * 管理员按照分组查询老人信息
     */
    @GetMapping("/table_officestaff_getoldmaninfoBygroup")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_officestaff_getoldmaninfoBygroup( Model model, HttpSession session){/*重定向，携带参数*/
        /*根据看护组长信息查出组号*/
       /* EspGroupInfo groupInfo =groupService.getOneInfobycaregiver(no);//获取到组长信息
        session.setAttribute("groupInfo",groupInfo);//将组号信息到界面*/
        //List<EspCaregivergroup> info=groupCaregiverService.getAllInfo();
        List<EspElderlyPhysicalData> info = elderlyPhysicalDataService.getAllOldmanInfoByGroup();//查出信息
        System.out.println("信息查询测试------------"+info);

        model.addAttribute("modelinfo",info);
        return "table/table_officestaff_getoldmaninfoBygroup";
    }

    /**
     * 查询所有非看护组长身份的且未分组看护人员
     */


}
