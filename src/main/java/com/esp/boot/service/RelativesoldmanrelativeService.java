package com.esp.boot.service;

import com.esp.boot.entity.EspRelativesoldmanrelative;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelativesoldmanrelativeService {

    /**
     * 通过编号查询绑定亲属
     * @param sn
     * @return
     */
    List<EspRelativesoldmanrelative> getAllInfoBySn(String sn);
    /**
     * 通过编号删除绑定关系
     */
    public boolean deleteinfo(Integer no);

    /**
     * 添加一个新的关系
     */
    public boolean insertinfo(EspRelativesoldmanrelative info);

    /**
     * 通过老人编号和亲属编号查询是否有信息
     */
    List<EspRelativesoldmanrelative> getSomeInfo( String sn,String sn2);
}
