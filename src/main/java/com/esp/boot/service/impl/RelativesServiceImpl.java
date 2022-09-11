package com.esp.boot.service.impl;

import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.mapper.RelativesMapper;
import com.esp.boot.service.RelativesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelativesServiceImpl implements RelativesService {

    @Autowired
    RelativesMapper relativesMapper;

    /*查询所有亲属信息*/
    @Override
    public List<EspRelativesinfo> getAllInfo() {
        return relativesMapper.getAllInfo();
    }

    @Override
    public EspRelativesinfo getOneInfo(String no) {
        return relativesMapper.getOneInfo(no);
    }

    @Override
    public boolean updatePwdRelatives(EspRelativesinfo info) {
        return relativesMapper.updatePwdRelatives(info);
    }
}
