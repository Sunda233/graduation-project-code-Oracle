package com.esp.boot.service.impl;


import com.esp.boot.entity.emp;
import com.esp.boot.mapper.empMapper;
import com.esp.boot.service.empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class empServiceImpl implements empService {
    @Autowired
    empMapper empMapper;
    @Override
    public List<emp> getAllInfo() {
        return empMapper.getAllInfo();
    }
}
