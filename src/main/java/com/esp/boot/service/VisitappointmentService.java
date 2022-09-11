package com.esp.boot.service;

import com.esp.boot.entity.EspVisitappointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitappointmentService {

    /**
     * 查询所有预约通知单
     */
    List<EspVisitappointment> getAllInfo();
    /**
     * 亲属预约看望老人（添加信息）
     */
    public boolean insertinfo(EspVisitappointment info);
    /**
     * 通过亲属编号查询预约通知单
     */
    List<EspVisitappointment> getSomeInfoByRelatives(String no);
    /**
     * 通过组号查询预约通知单
     */
    List<EspVisitappointment> getSomeInfoByCaregiver(Integer id);
    /**
     * 更新处理单状态
     */
    public boolean updateinfo(EspVisitappointment info);
    /**
     * 通过预约单编号查询预约信息
     */
    EspVisitappointment getOneInfo(Integer id);
    /**
     * 根据编号删除申请
     */
    public boolean deleteinfo(Integer id);
}
