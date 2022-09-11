package com.esp.boot.service;

import com.esp.boot.entity.EspCaregivergroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对分组等操作。
 * 人员进组出组
 */
public interface GroupCaregiverService {

    /**
     * 查询所有看护人员与组信息
     */
    List<EspCaregivergroup> getAllInfo();

    /**
     * 根据组号查询小组旗下组员
     */
    List<EspCaregivergroup> getAllInfoById(Integer no);
    /**
     * 根据组号查询小组成员（老人）
     * getSomeOldmanInfoByGroup
     */
    List<EspCaregivergroup> getSomeOldmanInfoByGroup(Integer no);

    /**
     * 分配看护人员进组(增加一条记录)
     */
    public boolean insertinfo(EspCaregivergroup info);

    /**
     *查询尚未进行组分配的人员
     */
    List<EspCaregivergroup> getSomeInfoByUnGroup();
    /**
     * 删除一条分组信息
     */
    public boolean deleteinfo(String no);
    /**
     * 修改看护人员分组信息
     */
    public boolean updateinfo(EspCaregivergroup info);
}
