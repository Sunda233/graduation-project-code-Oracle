package com.esp.boot.mapper;

import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.entity.emp;
import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.entity.EspGroupInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface empMapper {
    List<emp> getAllInfo();
}
