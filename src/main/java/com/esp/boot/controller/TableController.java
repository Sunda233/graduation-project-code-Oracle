/**
 * 处理所有table的
 */
package com.esp.boot.controller;

import com.esp.boot.bean.User;
import com.esp.boot.entity.EspOldmaninfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    /**
     * 查看所有老人信息
     * @param
     * @return
     */
 /*   @GetMapping(value = {"/table_oldman","/table_oldman.html"})
    public String basic_table(){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去查看老人界面跳转");
        System.out.println("###############################################");

        int i = 10/0;
        return "table/table_oldman";
    }*/

    /**
     *
     * @param a  不带请求参数或者参数类型不对  400；Bad Request  一般都是浏览器的参数没有传递正确
     * @return
     */
    @GetMapping("/basic_table")
    public String basic_table(@RequestParam("a") int a){

        int i = 10/0;

       /* List<EspOldmaninfo> oldmaninfos =Arrays.asList();*/

        return "table/basic_table";
    }


  /*  @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value="pn",defaultValue = "1") Integer pn, Model model){
        //表格内容的遍历
        response.sendError
     List<User> users = Arrays.asList(new User("zhangsan", "123456"),*//*放入前端*//*
                new User("lisi", "123444"),
                new User("haha", "aaaaa"),
                new User("hehe ", "aaddd"));
       model.addAttribute("users",users);

       if(users.size()>3){
            throw new UserTooManyException();
      }
        //从数据库中查出user表中的用户进行展示

        //构造分页参数
        Page<User> page = new Page<>(pn, 2);
        //调用page进行分页
        Page<User> userPage = userService.page(page, null);


//        userPage.getRecords()
//        userPage.getCurrent()
//        userPage.getPages()


        model.addAttribute("users",userPage);*/

      //  return "table/dynamic_table";
    //}
}
