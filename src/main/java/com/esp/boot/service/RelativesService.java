package com.esp.boot.service;

import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.entity.EspRelativesinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelativesService {

    /**
     * 查看所有亲属信息
     */
    List<EspRelativesinfo> getAllInfo();

    /**
     * 根据编号查找亲属
     */
    /**
     * 通过编号查询亲属信息
     * @param no
     * @return
     */
    EspRelativesinfo getOneInfo(String no);


    /**
     * 亲属修改密码
     */
    public boolean updatePwdRelatives(EspRelativesinfo info);


}
