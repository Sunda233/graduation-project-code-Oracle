package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspRelativesinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RelativesMapper {

    /*查询所有亲属信息*/
   // @Select("select * from esp_relativesinfo")/*注解模式*/
    List<EspRelativesinfo> getAllInfo();

    /**
     * 通过编号查询亲属信息
     * @param no
     * @return
     */
    EspRelativesinfo getOneInfo(@Param("no") String no);
    /**
     * 亲属人员修改密码
     */
    public boolean updatePwdRelatives(EspRelativesinfo info);
}
