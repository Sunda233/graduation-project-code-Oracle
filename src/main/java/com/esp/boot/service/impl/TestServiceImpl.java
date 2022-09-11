package com.esp.boot.service.impl;


import com.esp.boot.entity.EspRelativesinfo;
import com.esp.boot.mapper.TestMapper;
import com.esp.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;


    /*根据id查询信息*/
    @Override
    public EspRelativesinfo getinfoById(Integer id) {
        return testMapper.getinfoById(id);
    }

    @Override
    public void saveEspRelativesInfo(EspRelativesinfo espRelativesInfo) {

    }
}
