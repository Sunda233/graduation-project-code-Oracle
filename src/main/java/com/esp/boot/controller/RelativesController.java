package com.esp.boot.controller;

import com.esp.boot.Msg.RspMessage;
import com.esp.boot.entity.*;
import com.esp.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RelativesController {

    @Autowired
    RelativesService relativesService;
    @Autowired
    RelativesoldmanrelativeService relativesoldmanrelativeService;
    @Autowired
    OldmanService oldmanService;
    @Autowired
    ElderlyPhysicalDataService elderlyPhysicalDataService;
    @Autowired
    VisitappointmentService visitappointmentService;
    RspMessage rspMessage =new RspMessage();


    /**
     * 查询所有亲属信息(ttt)
     * @return
     */
   /* @ResponseBody
    @GetMapping(value = {"/getinfoRelatives","/getinfoRelatives.html"})
    public List<EspRelativesinfo> getinfoRelatives(){//@RequestParam("a") int a
        System.out.println("###############################################");
        System.out.println("进去查看亲属界面跳转");
        System.out.println("###############################################");
        //查出亲属信息
        relativesService.getAllInfo();
        System.out.println( relativesService.getAllInfo());
        return relativesService.getAllInfo();
    }*/


    /**
     * 查看所有亲属人员并转向信息页面信息
     * @param
     * @return 看护人员界面
     */
    @GetMapping(value = {"/table_relativesinfo","/table_relativesinfo.html"})
    public String toMain(Model model){
        System.out.println("###############################################");
        System.out.println("查看亲属界面跳转");
        System.out.println("###############################################");
        /*从数据库中查出信息进行展示*/
        List<EspRelativesinfo> info=relativesService.getAllInfo();
        //传向前端，将数据放进model
        model.addAttribute("modelinfo",info);
        return "table/table_relativesinfo";
    }

    /**
     * 根据亲属编号查看老人信息
     */
    @GetMapping("/table_relatives_getoldmaninfo")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String delete( Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        List<EspRelativesoldmanrelative> info = relativesoldmanrelativeService.getAllInfoBySn(no);
        // ra.addAttribute("pn",pn);
        System.out.println(info);
        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        session.setAttribute("info",relatives);
        model.addAttribute("modelinfo",info);
       // session.setAttribute("info",no);
        return "table/table_relatives_getoldmaninfo";
    }
    /**
     * 根据亲属编号查询老人信息的看护信息
     */
    @GetMapping("/table_relatives_getcaregiverinfo")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_relatives_getcaregiverinfo(Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        List<EspRelativesoldmanrelative> info = relativesoldmanrelativeService.getAllInfoBySn(no);
        // ra.addAttribute("pn",pn);
        System.out.println(info);
        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        session.setAttribute("info",relatives);
        model.addAttribute("modelinfo",info);
        // session.setAttribute("info",no);
        return "table/table_relatives_getcaregiverinfo";
    }
    /**
     * 根据亲属编号查看老人身体数据信息
     * 绑定老人的身体数据查看
     */
    @GetMapping(value = {"/select/relatives/ElderlyPhysicalData","/select/ElderlyPhysicalData.html"})
    public String toMain(Model model,HttpSession session){

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        System.out.println("###############################################");
        System.out.println("查看身体数据信息界面跳转--亲属人员");
        System.out.println("###############################################");
        /*根据看护组长信息查出组号*/
        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        session.setAttribute("info",relatives);//将亲属信息传到下一个界面
        /*从数据库中查出信息进行展示*/
        List<EspElderlyPhysicalData> info=elderlyPhysicalDataService.getSomeInfoByRelatives(no);//通过亲属编号查询老人身体数据
        //传向前端，将数据放进model
        // EspElderlyPhysicalData espElderlyPhysicalData;
        System.out.println("查看查询出的数据==========================="+info);
        model.addAttribute("modelinfo",info);
        return "table/table_relatives_ElderlyPhysicalData";
    }


    /**
     * 亲属执行绑定老人操作
     */
    @ResponseBody
    @PostMapping("/insert/relativesoldmanrelative")
    public RspMessage insert(String no,String oldmanIdnumber,String oldmanrelative){
        //子属性赋值
        //子属性赋值

        System.out.println("进入添加操作");
        EspRelativesoldmanrelative info=new EspRelativesoldmanrelative();
        EspOldmaninfo olaman = new EspOldmaninfo();
        EspRelativesinfo relative = new EspRelativesinfo();

        //亲属编号赋值
        relative.setRelativesSerialnumber(no);//亲属编号
        //通过身份证查询老人信息
        olaman= oldmanService.getOneInfoByIdN(oldmanIdnumber);
        //填充信息
        info.setOldmaninfo(olaman);
        info.setRelativesinfo(relative);
        info.setRelativesrelative(oldmanrelative);
        System.out.println("数据"+info);
        //查询数据库中是否已经有此条信息
        List<EspRelativesoldmanrelative> haveinfo = relativesoldmanrelativeService.getSomeInfo(no,olaman.getOldmanSerialnumber());
        System.out.println("查询值测试"+haveinfo);
        if (haveinfo.size()==0){//如果没有数据
            //执行添加操作
            boolean bool= relativesoldmanrelativeService .insertinfo(info);
            if (bool==true){
                rspMessage.setMessage("添加成功");
                rspMessage.setMsgCode("2000");
            }else {
                rspMessage.setMessage("出现错误");
                rspMessage.setMsgCode("2001");
            }
        }else{
            System.out.println("已经添加过该信息");
            rspMessage.setMessage("已经添加过该信息！");
            rspMessage.setMsgCode("2004");
            return rspMessage;
        }

        return rspMessage;
    }


    /**
     * 亲属执行解绑老人操作,通过编号删除
     */
    @ResponseBody
    @PostMapping("/delete/relativesoldmanrelative")
    public RspMessage delete(@RequestParam("no") Integer no){
        //执行删除操作
        boolean bool=  relativesoldmanrelativeService.deleteinfo(no);
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
     * 亲属修改个人信息
     */

    /**
     * 根据亲属编号查询预约通知单，并转到详情界面
     */

    @GetMapping("relatives/table_relatives_Visitappointment")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String select(Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        List<EspVisitappointment> info = visitappointmentService.getSomeInfoByRelatives(no);//根据获取的亲属编号查询信息
        System.out.println("查询测试============"+info);//输出查询信息
        model.addAttribute("modelinfo",info);

        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        session.setAttribute("info",relatives);//亲属信息放入session
        return "table/table_relatives_Visitappointment";
    }

    /**
     * 根据亲属编号查看个人信息
     */
    @GetMapping("/infoOneself_relatives")/*@PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值*/
    public  String table_relatives_infoOneself( Model model,HttpSession session){/*重定向，携带参数*/

        /**
         * 获取session中的值
         */
        String no= (String) session.getAttribute("usernamerelatives");
        System.out.println("session获取值测试-------------------------------"+no);

        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        session.setAttribute("info",relatives);
        model.addAttribute("modelinfo",relatives);
        return "infooneself/infoOneself_relatives";
    }

    /**
     * 根据亲属编号修改密码
     */
    @ResponseBody
    @PostMapping("/update/relativesPwd")
    public RspMessage caregiverleaderpwd(@RequestParam("no") String no,@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd){
        //查询旧密码是否正确，如果正确修改密码，如果错误不修改
        //查询旧密码
        EspRelativesinfo relatives=relativesService.getOneInfo(no);//根据编号查询亲属信息
        System.out.println("***********************************查询信息"+relatives);
        if (relatives.getRelativesPassword().equals(oldpwd)){
            //修改密码
            //执行操作
            relatives.setRelativesPassword(newpwd);
            boolean bool=  relativesService.updatePwdRelatives(relatives);
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
