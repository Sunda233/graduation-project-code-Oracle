package com.esp.boot.service.impl;

import com.esp.boot.entity.EspCaregivergroup;
import com.esp.boot.mapper.GroupCaregiverMapper;
import com.esp.boot.service.GroupCaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupCaregiverServiceImpl implements GroupCaregiverService {

    @Autowired
    GroupCaregiverMapper groupCaregiverMapper;

    @Override
    public List<EspCaregivergroup> getAllInfo() {
        return groupCaregiverMapper.getAllInfo();
    }

    @Override
    public List<EspCaregivergroup> getAllInfoById(Integer no) {
        return groupCaregiverMapper.getAllInfoById(no);
    }

    @Override
    public List<EspCaregivergroup> getSomeOldmanInfoByGroup(Integer no) {
        return groupCaregiverMapper.getSomeOldmanInfoByGroup(no);
    }

    @Override
    public boolean insertinfo(EspCaregivergroup info) {
        return groupCaregiverMapper.insertinfo(info);
    }

    @Override
    public List<EspCaregivergroup> getSomeInfoByUnGroup() {
        return groupCaregiverMapper.getSomeInfoByUnGroup();
    }

    @Override
    public boolean deleteinfo(String no) {
        return groupCaregiverMapper.deleteinfo(no);
    }

    @Override
    public boolean updateinfo(EspCaregivergroup info) {
        return groupCaregiverMapper.updateinfo(info);
    }
}
