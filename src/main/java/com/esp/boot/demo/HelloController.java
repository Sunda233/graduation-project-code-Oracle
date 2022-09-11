package com.esp.boot.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {


    /**
     * rest风格使用
     * 现在： /user    GET-获取用户    DELETE-删除用户     PUT-修改用户      POST-保存用户
     * @return
     */
    //    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){

        return "GET-张三";
    }

    //    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }


    //    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){

        return "PUT-张三";
    }

    @DeleteMapping("/user")
//    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public String deleteUser(){
        return "DELETE-张三";
    }




}
