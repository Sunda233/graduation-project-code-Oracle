package com.esp.boot.service.impl;

import com.esp.boot.entity.EspVisitappointment;
import com.esp.boot.mapper.VisitappointmentMapper;
import com.esp.boot.service.VisitappointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitappointmentServiceImpl implements VisitappointmentService {
    @Autowired
    VisitappointmentMapper mapper;
    @Override
    public List<EspVisitappointment> getAllInfo() {
        return mapper.getAllInfo();
    }

    @Override
    public boolean insertinfo(EspVisitappointment info) {
        return mapper.insertinfo(info);
    }

    @Override
    public List<EspVisitappointment> getSomeInfoByRelatives(String no) {
        return mapper.getSomeInfoByRelatives(no);
    }

    @Override
    public List<EspVisitappointment> getSomeInfoByCaregiver(Integer id) {
        return mapper.getSomeInfoByCaregiver(id);
    }

    @Override
    public boolean updateinfo(EspVisitappointment info) {
        return mapper.updateinfo(info);
    }

    @Override
    public EspVisitappointment getOneInfo(Integer id) {
        return mapper.getOneInfo(id);
    }

    @Override
    public boolean deleteinfo(Integer id) {
        return mapper.deleteinfo(id);
    }

}
