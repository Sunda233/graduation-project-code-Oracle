package com.esp.boot.mapper;

import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspGroupInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对于组中看护人员信息的操作
 */
public interface GroupCaregiverMapper {

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
     *查询尚未分配进组的看护人员
     */
    List<EspCaregivergroup> getSomeInfoByUnGroup();
    /**
     * 添加一条新的分组信息
     */
    /**
     * 删除看护人员分组
     */
    public boolean deleteinfo(@Param("no") String no);

    /**
     * 修改看护人员进组信息
     */
    public boolean updateinfo(EspCaregivergroup info);


}
