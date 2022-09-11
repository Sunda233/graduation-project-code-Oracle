package com.esp.boot.service.impl;


import com.esp.boot.entity.EspCaregiverinfo;
import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.mapper.CaregiverMapper;
import com.esp.boot.service.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaregiverServiceImpl implements CaregiverService {
    @Autowired
    CaregiverMapper caregiverMapper;
    @Override
    public List<EspCaregiverinfo> getAllInfo() {
        return caregiverMapper.getAllInfo();
    }
    @Override
    public List<EspCaregiverleaderinfo> getAllleaderInfo() {
        return caregiverMapper.getAllleaderInfo();
    }
    @Override
    public boolean insertinfo(EspCaregiverinfo info) {
        return caregiverMapper.insertinfo(info);
    }

    @Override
    public boolean updateinfo(EspCaregiverinfo info) {
        return caregiverMapper.updateinfo(info);
    }

    @Override
    public boolean deleteinfo(String no) {
        return caregiverMapper.deleteinfo(no);
    }
    @Override
    public EspCaregiverinfo getOneInfo(String no) {
        return caregiverMapper.getOneInfo(no);
    }

    @Override
    public List<EspCaregiverinfo> getSomeInfoByUnLeader() {
        return caregiverMapper.getSomeInfoByUnLeader();
    }

    @Override
    public boolean insertinfocaregiverleader(EspCaregiverleaderinfo info) {
        return caregiverMapper.insertinfocaregiverleader(info);
    }

    @Override
    public boolean deleteinfocaregiverleader(String no) {
        return caregiverMapper.deleteinfocaregiverleader(no);
    }

    @Override
    public boolean updateinfocaregiverleader(EspCaregiverleaderinfo info) {
        return caregiverMapper.updateinfocaregiverleader(info);
    }

    @Override
    public List<EspCaregiverleaderinfo> getSomeleaderInfoByUnGroup() {
        return caregiverMapper.getSomeleaderInfoByUnGroup();
    }

    @Override
    public EspCaregiverleaderinfo getoneleaderInfo(String no) {
        return caregiverMapper.getoneleaderInfo(no);
    }
}
