package com.esp.boot.service;

import com.esp.boot.entity.EspElderlyPhysicalData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElderlyPhysicalDataService {
    /**
     * 查询所有身体数据
     */
    List<EspElderlyPhysicalData> getAllInfo();
    /**
     * 查询本组老人身体数据
     */
    List<EspElderlyPhysicalData> getSomeInfoByGroup(Integer no);

    /**
     * 根据组号查询老人信息
     */
    List<EspElderlyPhysicalData> getAllInfoByGroup(Integer no);

    /**
     * 添加新数据表sdd
     */
    public boolean insertinfo(EspElderlyPhysicalData info);
    /**
     * 通过编号删除身体数据信息
     */
    public boolean deleteinfo(Integer no);
    /**
     * 修改身体数据信息
     */
    public boolean updateinfo(EspElderlyPhysicalData info);

    /**
     * 通过亲属编号查询老人身体数据信息
     */
    List<EspElderlyPhysicalData> getSomeInfoByRelatives(String no);
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
     *
     */
    List<EspElderlyPhysicalData> getSomeOldmanInfoByGroup(@Param("no") Integer no);

}
