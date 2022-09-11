package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.entity.EspGroupInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对于身体数据表信息的操作
 */
@Mapper
public interface ElderlyPhysicalDataMapper {
    /**
     * 查看所有身体健康表信息
     */
     List<EspElderlyPhysicalData> getAllInfo();


    /**
     * 根据组号已有查询身体数据信息
     */
    List<EspElderlyPhysicalData> getSomeInfoByGroup(@Param("no") Integer no);

    /**
     * 根据组号查询本组老人
     * @param no
     * @return
     */
    List<EspElderlyPhysicalData> getAllInfoByGroup(@Param("no") Integer no);

    /**
     * 添加新身体数据表
     */
    public boolean insertinfo(EspElderlyPhysicalData info);
    /**
     * 通过编号删除身体数据信息
     */
    public boolean deleteinfo(@Param("no") Integer no);
    /**
     * 修改身体数据表信息
     */
    public boolean updateinfo(EspElderlyPhysicalData info);
    /**
     * 通过亲属编号查询身体数据信息
     */
    List<EspElderlyPhysicalData> getSomeInfoByRelatives(@Param("no") String no);
    /**
     * 管理员查询亲属绑定信息
     */
    List<EspElderlyPhysicalData> getAllInfoByStaff();
    /**
    * 查询所有组的所有老人信息
    */
  List<EspElderlyPhysicalData> getAllOldmanInfoByGroup();

    /**
     * 根据组号查询老人信息
     * getSomeOldmanInfoByGroup
     */
    List<EspElderlyPhysicalData> getSomeOldmanInfoByGroup(@Param("no") Integer no);
}
