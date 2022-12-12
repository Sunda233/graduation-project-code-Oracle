package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.entity.emp;
import com.esp.boot.service.GroupCaregiverService;
import com.esp.boot.service.GroupService;
import com.esp.boot.service.empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class empController {
    @Autowired
    empService empService;
    GroupService groupService;

    RspMessage rspMessage = new RspMessage();

    /**
     * 查询emp表信息
     */
    @ResponseBody
    @GetMapping("/select/emp")
    public List<emp> getSomeInfoByUnGroup(){
        //查询尚未分组的组员信息
        List<emp> info=  empService.getAllInfo();
        System.out.println(info);
        return info;
    }

}
