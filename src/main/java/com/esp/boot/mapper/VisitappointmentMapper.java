package com.esp.boot.mapper;

import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.entity.EspRelativesoldmanrelative;
import com.esp.boot.entity.EspVisitappointment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预约单通知
 */
public interface VisitappointmentMapper {

    /**
     * 查询所有预约通知单
     */
    List<EspVisitappointment> getAllInfo();
    /**
     * 亲属预约看望老人（添加信息）
     */
    public boolean insertinfo(EspVisitappointment info);

    /**
     * 通过组号查询预约通知单
     */
    List<EspVisitappointment> getSomeInfoByCaregiver(@Param("id") Integer id);

    /**
     * 通过亲属编号查询预约通知
     */
    List<EspVisitappointment> getSomeInfoByRelatives(@Param("no") String no);

    /**
     * 更新处理单状态
     */
    public boolean updateinfo(EspVisitappointment info);
    /**
     * 通过预约单编号查询预约信息
     */
    EspVisitappointment getOneInfo(@Param("id") Integer id);

    /**
     * 根据编号删除处理单，取消申请
     * @param
     * @return
     */
    public boolean deleteinfo(@Param("id") Integer id);

}
