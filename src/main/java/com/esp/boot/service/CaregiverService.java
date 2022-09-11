package com.esp.boot.service;

import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
*
 * 看护人员数据

*/

public interface CaregiverService {
/**
     * 查看所有看护人员
    */

    List<EspCaregiverinfo> getAllInfo();


/**
     * 查看所有具有看护组长身份的看护人员
    */

    public List<EspCaregiverleaderinfo> getAllleaderInfo();


/**
     * 按照ID查看看护人员
     */


/**
     * 按照组号查看看护人员
     */


/**
     * 新增看护人员信息
    */
   public boolean insertinfo(EspCaregiverinfo info);
/**
     * 修改看护人员信息
     */
   public boolean updateinfo(EspCaregiverinfo info);

    /**
     * 根据工号删除看护人员信息
     */
    public boolean deleteinfo(String no);
    /**
     * 根据工号查询看护人员信息
     */
    EspCaregiverinfo getOneInfo(String no);

    /**
     * 查询所有非看护组长信息的看护人员
     */
    List<EspCaregiverinfo> getSomeInfoByUnLeader();
/*===========================================看护组长功能=============================================*/
    /**
     * 添加看护组长信息
     */
    public boolean insertinfocaregiverleader(EspCaregiverleaderinfo info);
    /**
     * 删除看护组长信息
     */
    public boolean deleteinfocaregiverleader(String no);

    /**
     * 修改看护人员密码
     */
    public boolean updateinfocaregiverleader(EspCaregiverleaderinfo info);

    /**
     * 获取所有未分组的看护组长信息
     */
    List<EspCaregiverleaderinfo> getSomeleaderInfoByUnGroup();


    /**
     * 看护组长查看个人信息
     */
    EspCaregiverleaderinfo getoneleaderInfo(String no);

}
