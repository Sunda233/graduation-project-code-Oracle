package com.esp.boot.mapper;

import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.entity.EspOldmaninfo;
import com.esp.boot.entity.EspRelativesoldmanrelative;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老人与亲属绑定关系表
 */
public interface RelativesoldmanrelativeMapper {
    /**
     * 通过亲属编号查询已绑定老人信息
     * @return
     */
    List<EspRelativesoldmanrelative> getAllInfoBySn(@Param("sn") String sn);

    /**
     * 通过编号删除绑定关系
     */
    public boolean deleteinfo(@Param("no")Integer no);

    /**
     * 添加一个新的绑定关系
     */
    public boolean insertinfo(EspRelativesoldmanrelative info);
    /**
     * 通过老人编号和亲属编号查询是否有信息
     */
    List<EspRelativesoldmanrelative> getSomeInfo(@Param("sn") String sn,@Param("sn2") String sn2);

}
