package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspOldmaninfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对于看护人员信息的操作
 */
@Mapper
public interface CaregiverMapper {
    /**
     * 查看所有的看护人员
     */
   // @Select("select * from esp_caregiverinfo")/*注解模式*/
    List<EspCaregiverinfo> getAllInfo();

    /**
     * 根据工号删除看护人员信息
     */
    public boolean deleteinfo(@Param("no") String no);
    /**
     * 添加一条看护人员信息
     */
    public boolean insertinfo(EspCaregiverinfo info);
    /**
     * 通过编号查询看护人员信息
     */
    EspCaregiverinfo getOneInfo(@Param("no") String no);
    /**
     * 修改看护人员信息
     */
    public boolean updateinfo(EspCaregiverinfo info);
    /**
     * 根据组号查询看护人员信息
     */
    List<EspCaregiverinfo> getAllInfoByGroupid( Integer no);
    /**
     * 查询所有非看护组长信息的看护人员
     */
    List<EspCaregiverinfo> getSomeInfoByUnLeader();
/*====================================看护组长操作============================================*/
    /**
     * 查询所有具有看护组长身份的看护人员
     */
    List<EspCaregiverleaderinfo> getAllleaderInfo();
    /**
     * 添加看护组长信息
     */
    public boolean insertinfocaregiverleader(EspCaregiverleaderinfo info);
    /**
     * 删除看护组长信息
     */
    public boolean deleteinfocaregiverleader(@Param("no") String no);

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
    EspCaregiverleaderinfo getoneleaderInfo(@Param("no") String no);
}
