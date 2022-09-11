package com.esp.boot.mapper;


import com.esp.boot.entity.EspRelativesinfo;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface TestMapper {

    @Select("select * from esp_relativesinfo where relatives_id=#{id}")/*注解模式*/
    public EspRelativesinfo getinfoById(Integer id);

    /*可以使用配置文件方式*/

   /* @Options(useGeneratedKeys = true,keyProperty = "id")*//*注解模式使用自增主键，主键为id*/
    public void insert(EspRelativesinfo EspRelativesInfo);
}
