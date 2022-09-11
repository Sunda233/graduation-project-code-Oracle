package com.esp.boot.service.impl;

import com.esp.boot.entity.EspGroupInfo;
import com.esp.boot.mapper.GroupMapper;
import com.esp.boot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupMapper groupMapper;
    @Override
    public List<EspGroupInfo> getAllInfo() {
        return groupMapper.getAllInfo();
    }

    @Override
    public List<EspGroupInfo> getAllgroupId() {
        return groupMapper.getAllgroupId();
    }

    @Override
    public boolean insertinfo(EspGroupInfo info) {
        return groupMapper.insertinfo(info);
    }
    @Override
    public boolean deleteinfo(Integer no) {
        System.out.println("小组信息删除测试"+no);
        return groupMapper.deleteinfo(no);
    }
    @Override
    public EspGroupInfo getOneInfo(String no) {
        return groupMapper.getOneInfo(no);
    }

    @Override
    public boolean updateinfo(EspGroupInfo info) {
        return groupMapper.updateinfo(info);
    }

    @Override
    public EspGroupInfo getOneInfobycaregiver(String no) {
        return groupMapper.getOneInfobycaregiver(no);
    }
}
