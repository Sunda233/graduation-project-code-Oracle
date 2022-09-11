package com.esp.boot.service;

/**
 * 对组本身的操作
 */

import com.esp.boot.entity.EspGroupInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupService {
    /**
     * 查询所有组的信息
     * （组号，组长，组长姓名）
     */
    List<EspGroupInfo> getAllInfo();
    //查询全部组号
    List<EspGroupInfo> getAllgroupId();

    /**
     * 添加一个新的分组
     */
    public boolean insertinfo(EspGroupInfo info);

    /**
     * 删除分组信息
     */
    public boolean deleteinfo(Integer no);

    /**
     * 根据编号查询小组本身信息
     */
    EspGroupInfo getOneInfo(String no);
    /**
     * 修改分组信息
     */
    public boolean updateinfo(EspGroupInfo info);

    /**
     * 通过组长工号获得组号和组长信息
     */
    EspGroupInfo getOneInfobycaregiver( String no);
}

