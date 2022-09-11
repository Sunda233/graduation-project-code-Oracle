package com.esp.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esp.boot.entity.EspOldmaninfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*Mapper继承该接口后，无需编写mapper.xml文件，即可获得crud功能*/
public interface OldManMapper extends BaseMapper<EspOldmaninfo> {/*整合mybatis-plus，会有一个基类mapper*/

    /*查询所有组信息*/
   /* @Select("select * from esp_oldmaninfo")*//*注解模式*/
    List<EspOldmaninfo> getAllInfo();/*EspOldmaninfo espOldmaninfo*/
    /**
     * 根据编号删除老人信息
     */
    public boolean deleteinfo(String oldmanSerialnumber);

    /**
     * 添加一条老人信息
     */
    public boolean insertinfo(EspOldmaninfo espOldmaninfo);

    /**
     * 修改老人信息
     */
    public boolean updateinfo(EspOldmaninfo espOldmaninfo);
    /**
     * 通过编号查询老人信息
     */
    EspOldmaninfo getOneInfo(@Param("oldmanSerialnumber") String oldmanSerialnumber);
    /**
     * 通过身份证号查询老人信息
     */
    EspOldmaninfo getOneInfoByIdN(@Param("oldmanIdnumber") String oldmanIdnumber);
    //查看组信息
    //查看组中成员信息
}
