package com.esp.boot.service.impl;

import com.esp.boot.entity.EspElderlyPhysicalData;
import com.esp.boot.mapper.ElderlyPhysicalDataMapper;
import com.esp.boot.service.ElderlyPhysicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyPhysicalDataServiceImpl implements ElderlyPhysicalDataService {
    @Autowired
    ElderlyPhysicalDataMapper elderlyPhysicalDataMapper;
    @Override
    public List<EspElderlyPhysicalData> getAllInfo() {
        return elderlyPhysicalDataMapper.getAllInfo();
    }

    @Override
    public List<EspElderlyPhysicalData> getSomeInfoByGroup(Integer no) {
        return elderlyPhysicalDataMapper.getSomeInfoByGroup(no);
    }

    @Override
    public List<EspElderlyPhysicalData> getAllInfoByGroup(Integer no) {
        return elderlyPhysicalDataMapper.getAllInfoByGroup(no);
    }

    @Override
    public boolean insertinfo(EspElderlyPhysicalData info) {
        return elderlyPhysicalDataMapper.insertinfo(info);
    }

    @Override
    public boolean deleteinfo(Integer no) {
        return elderlyPhysicalDataMapper.deleteinfo(no);
    }

    @Override
    public boolean updateinfo(EspElderlyPhysicalData info) {
        return elderlyPhysicalDataMapper.updateinfo(info);
    }

    @Override
    public List<EspElderlyPhysicalData> getSomeInfoByRelatives(String no) {
        return elderlyPhysicalDataMapper.getSomeInfoByRelatives(no);
    }

    @Override
    public List<EspElderlyPhysicalData> getAllInfoByStaff() {
        return elderlyPhysicalDataMapper.getAllInfoByStaff();
    }

    @Override
    public List<EspElderlyPhysicalData> getAllOldmanInfoByGroup() {
        return elderlyPhysicalDataMapper.getAllOldmanInfoByGroup();
    }

    @Override
    public List<EspElderlyPhysicalData> getSomeOldmanInfoByGroup(Integer no) {
        return elderlyPhysicalDataMapper.getSomeOldmanInfoByGroup(no);
    }
}
