package com.esp.boot.service;

import com.esp.boot.entity.EspChatlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatlogService {
    /**
     * 看护人员查询收件箱
     */
 /*   List<EspChatlog> getSomeInfoByCaregivershou(String no);
    *//**
     * 看护人员查询发件箱
     *//*
    List<EspChatlog> getSomeInfoByCaregiverfa(String no);*/
    List<EspChatlog> getSomeInfoByCaregiver(String no,Integer shoufa);
    /**
     * 亲属查询收件箱发件箱
     */
    List<EspChatlog> getSomeInfoByRelatives(String no,Integer shoufa);
    /**
     * 通过ID查询
     */
    EspChatlog getOneInfoByID(Integer id);
    /**
     * 添加一条站内信
     */
    public boolean insertinfo(EspChatlog info);
    /**
     * 看护删除私信
     *
     * @return
     */
    public boolean deleteinfoByCaregiver(Integer id);

    /**
     * 亲属删除私信
     */
    public boolean deleteinfoByRelatives(Integer id);

    /**
     * 真正删除私信
     */
    public boolean deleteinfo(Integer id);
}
