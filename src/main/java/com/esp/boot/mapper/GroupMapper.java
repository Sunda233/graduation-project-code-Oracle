package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspGroupInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 对于看护人员信息的操作
 */
public interface GroupMapper {
    /**
     * 查看所有的组的信息
     */
   // @Select("select * from esp_caregiverinfo")/*注解模式*/
    List<EspGroupInfo> getAllInfo();

    //查询全部组号
    List<EspGroupInfo> getAllgroupId();

    /**
     * 添加一个新分组
     */
    public boolean insertinfo(EspGroupInfo info);

    /**
     * 删除分组信息
     */
    public boolean deleteinfo(@Param("no") Integer no);

    /**
     * 根据编号查询分组信息
     */
    EspGroupInfo getOneInfo(@Param("no") String no);
    /**
     * 修改分组信息
     */
    public boolean updateinfo(EspGroupInfo info);
    /**
     * 通过组长工号获得组号与组长信息
     */
    EspGroupInfo getOneInfobycaregiver(@Param("no") String no);

}
