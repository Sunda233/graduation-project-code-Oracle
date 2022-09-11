package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspOfficestaff;
import com.esp.boot.entity.EspRelativesinfo;
import org.apache.ibatis.annotations.Param;

/**
 * 查询所有账号信息，用于登录与注册
 */
public interface IndexMapper {
    /**
     * 查询管理员账号
     */
    public EspOfficestaff selectStaff(@Param("username") String username, @Param("password")String password);/*必须使用Param，否则无法传值*/
    /**
     * 查询看护组长账号
     */
    public EspCaregiverleaderinfo selectCaregiver(@Param("username") String username, @Param("password")String password);
    /**
     * 查询亲属人员账号
     */
    public EspRelativesinfo selectRelatives(@Param("username") String username, @Param("password")String password);

    /**
     * 添加一条亲属账号信息
     */
    public boolean insert(EspRelativesinfo espRelativesinfo);
}
