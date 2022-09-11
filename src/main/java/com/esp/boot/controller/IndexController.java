/**
 * 负责页面跳转
 * 所有在tempets的页面都需要页面跳转
 * 模板引擎所有的请求处理必须经过解析
 */
package com.esp.boot.controller;

import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspOfficestaff;
import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;
    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})//发送此两个跳转到登录页
    public String loginPage(){
        System.out.println("登录界面跳转");
        return "login";  //login
    }
    /*注册页面*/
    @GetMapping(value = {"/register","/register.html"})//发送此两个跳转到登录页
    public String registerPage(){
        System.out.println("注册界面跳转");
        return "register";
    }

    /*main页面的提交 */
    @PostMapping("/login")
    //username和input中的name名称一样是才可以获得值，给登录页要去的所有内容放在model里面 sunda毕设
    public String main(String username,String password,HttpSession session, Model model){ //RedirectAttributes
         System.out.println("登录界面post跳转");
         //用户名有长度和密码为123456，后期会改
        System.out.println("获得的用户名"+username);
        System.out.println("获得的密码"+password);
        System.out.println("###############################################");

        Integer flag=-1;
        EspOfficestaff staff =indexService.selectStaff(username,password);
        EspCaregiverleaderinfo caregiverleader =indexService.selectCaregiver(username,password);
        EspRelativesinfo relatives=indexService.selectRelatives(username,password);//手机号与密码
        System.out.println("办公人员登陆"+staff);
        System.out.println("看护组长登录"+caregiverleader);
        System.out.println("======================================="+caregiverleader);
        System.out.println("登陆亲属"+relatives);
        //逻辑判断并传值
        System.out.println("##########################进入密码验证#####################");
        if ( staff!=null){//密码正确
                System.out.println("进办公人员界面");
                session.setAttribute("username",username);
                session.setAttribute("info",staff);
                model.addAttribute("modelinfo",staff);
                return "redirect:/index.html";
        } else if ( caregiverleader!=null) {
                System.out.println("进看护组长界面");
                session.setAttribute("username",username);
                session.setAttribute("info",caregiverleader.getCaregiverinfo());
                model.addAttribute("modelinfo",caregiverleader.getCaregiverinfo());
                System.out.println(""+caregiverleader);
                return "redirect:/main_caregiver";
        }else if (relatives!=null) {
                System.out.println("进亲戚界面");
                session.setAttribute("username",username);
                session.setAttribute("usernamerelatives",relatives.getRelativesSerialnumber());
                session.setAttribute("info",relatives);
                model.addAttribute("modelinfo",relatives);
                return "redirect:/main_relatives";
        }else {
            model.addAttribute("msg","账号密码错误");
            //回到登录页面
            return "login";
        }
    }

    /**
     * 去main页面
     * @return
     */
    /*直接访问不能够访问到templass的页面，只能访问到静态文件夹的页面*/
    @GetMapping("/index.html")
    public String indexPage(HttpSession session,Model model){

   /*  //   log.info("当前方法是：{}","mainPage");
        //是否登录。  拦截器，过滤器
      Object loginUser = session.getAttribute("username");
        if(loginUser != null){
           return "index";
       }else {
            //回到登录页面
          model.addAttribute("msg","请重新登录");
           return "login";
       }*/

//        ValueOperations<String, String> opsForValue =
//                redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);

        return "index";
    }
    /**
     * 管理员测试—去看护人员界面
     */
    @GetMapping(value = {"/main_caregiver"})//发送此两个跳转到登录页
    public String CaregiverPage(){
        System.out.println("看护人员界面跳转");
        return "main_caregiverleader";
    }

    /**
     * 管理员测试，去亲属界面
     * @return
     */
    @GetMapping(value = {"/main_relatives"})//发送此两个跳转到登录页
    public String main_relatives(){
        System.out.println("亲属人员界面跳转");
        return "main_relatives";
    }


    /**
     * 注册亲属信息
     * @return
     */
    @PostMapping(value = {"/insertRelatives"})//发送此两个跳转到登录页
    public String insert(String relativeName,String relativePassword,String relativePhone,HttpSession session, Model model){
        EspRelativesinfo espRelativesinfo = new EspRelativesinfo();
        espRelativesinfo.setRelativesSerialnumber(suijishu(2));
        espRelativesinfo.setRelativesName(relativeName);
        espRelativesinfo.setRelativesPhone(relativePhone);
        espRelativesinfo.setRelativesPassword(relativePassword);
        //将对象添加进数据库
        System.out.println("要添加的数据"+espRelativesinfo);
       boolean bool =indexService.insert(espRelativesinfo);
       if (bool=true){
           System.out.println("添加成功");
           model.addAttribute("msg","注册成功请重新登录");
           return "login";
       }else{
           System.out.println("添加失败");
           model.addAttribute("msg","注册失败请重新尝试");
           return "login";
       }
      /*  return "redirect:/register.html";*/



    }

    /**
     * 随机数生成
     * @return
     */
    public static String suijishu(Integer oneno){
        Random rand = new Random();
        Integer no=(oneno*100000000)+1;
        Integer no2=rand.nextInt(100000000);
        no=no+no2;
        System.out.println("随机数生成测试"+no);
        return no.toString();
    }


    /*  if(tag!=1){
            rspMessage.setMsgCode("2002");
            rspMessage.setMessage("数据库插入数据不成功");
            return rspMessage;
        }
        rspMessage.setMessage("恭喜你注册成功");
        rspMessage.setMsgCode("2000");
        return rspMessage;
*/

    /**
     * 退出系统
     */
    @GetMapping(value = {"/logout"})//发送此两个跳转到登录页
    public String logout(HttpSession session){
        session.setAttribute("username",null);
        session.setAttribute("usernamerelatives",null);
        System.out.println("退出后清理缓存"+session.getAttribute("username"));
        System.out.println("退出后清理缓存"+session.getAttribute("usernamerelatives"));
        return "/login";
    }


}
