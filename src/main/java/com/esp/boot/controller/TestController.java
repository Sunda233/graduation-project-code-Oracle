package com.esp.boot.controller;


import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestController {

    @Autowired
    TestService testService;

    /*查询并且返回对象*/
    @ResponseBody
    @GetMapping("/test")/*http://localhost:8080/test?id=1*/
    public EspRelativesinfo getinfoById(@RequestParam("id") Integer id){/*按照数据的id找到数据*//*RequestParam将请求参数绑定到你控制器的方法参数上*/
        log.info("controller层");
        log.info(String.valueOf(id));
        System.out.println(testService.getinfoById(id));
       return testService.getinfoById(id);
    }

    /*保存一条数据，测试插入数据*/
    /*Post请求需要使用表单*/
    /*目前不能使用*/
    @ResponseBody
    @PostMapping("/insert")
    public EspRelativesinfo saveCity(EspRelativesinfo espRelativesInfo){
        testService.saveEspRelativesInfo(espRelativesInfo);
        return espRelativesInfo;
    }





}
