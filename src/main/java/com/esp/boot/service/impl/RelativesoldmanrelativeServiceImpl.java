package com.esp.boot.service.impl;

import com.esp.boot.entity.EspRelativesoldmanrelative;
import com.esp.boot.mapper.RelativesoldmanrelativeMapper;
import com.esp.boot.service.RelativesoldmanrelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelativesoldmanrelativeServiceImpl implements RelativesoldmanrelativeService {
    @Autowired
    RelativesoldmanrelativeMapper mapper;

    @Override
    public List<EspRelativesoldmanrelative> getAllInfoBySn(String sn) {
        return mapper.getAllInfoBySn(sn);
    }

    @Override
    public boolean deleteinfo(Integer no) {
        return mapper.deleteinfo(no);
    }

    @Override
    public boolean insertinfo(EspRelativesoldmanrelative info) {
        return mapper.insertinfo(info);
    }

    @Override
    public List<EspRelativesoldmanrelative> getSomeInfo(String sn, String sn2) {
        return mapper.getSomeInfo(sn,sn2);
    }
}
