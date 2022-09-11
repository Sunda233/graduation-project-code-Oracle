package com.esp.boot.service.impl;

import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspOfficestaff;
import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.mapper.IndexMapper;
import com.esp.boot.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexMapper indexMapper;

    /**
     * 查询办公人员信息，直接返回数据
     * @param username
     * @param password
     * @return
     */
    @Override
    public EspOfficestaff selectStaff(String username, String password) {
        //boolean bool=false;
      //  EspOfficestaff espOfficestaff=
      //  System.out.println("管理员登录查找测试"+espOfficestaff);
      /*  if(espOfficestaff!=null){//如果值不为空
        if (espOfficestaff.getOfficestaffEmpno().equals(username)&& espOfficestaff.getOfficestaffPassword().equals(password)){
            bool=true;
        }
        }*/
        return indexMapper.selectStaff(username,password);
    }
    @Override
    public EspCaregiverleaderinfo selectCaregiver(String username, String password) {
       // boolean bool=false;
        /*EspCaregiverleaderinfo info= indexMapper.selectCaregiver(username,password);
        System.out.println("看护人员登录查找测试"+info);*/
     /*   if(info!=null){//如果值不为空
            if (info.getCaregiverinfo().getCaregiverEmpno().equals(username)&& info.getCareLeaderInfoPassword().equals(password) ){//匹配值是否正确
                bool=true;
            }
        }*/
        return indexMapper.selectCaregiver(username,password);
    }

    @Override
    public EspRelativesinfo selectRelatives(String username, String password) {
        //boolean bool=false;
      /*  EspRelativesinfo info= indexMapper.selectRelatives(username,password);
        System.out.println(info);
       *//* if(info!=null){//如果值不为空
        if (info.getRelativesPhone().equals(username)&& info.getRelativesPassword().equals(password) ){
            bool=true;
         }
        }*/
      //  System.out.println("查询测试**************************************"+bool);
        return indexMapper.selectRelatives(username,password);
    }

    /**
     * 添加亲属信息测试
     * @param espRelativesinfo
     * @return
     */
    @Override
    public boolean insert(EspRelativesinfo espRelativesinfo) {
        return indexMapper.insert(espRelativesinfo);
    }
}
