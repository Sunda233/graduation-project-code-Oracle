package com.esp.boot.service.impl;

import com.esp.boot.entity.EspChatlog;
import com.esp.boot.mapper.ChatlogMapper;
import com.esp.boot.service.ChatlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatlogServiceaimpl implements ChatlogService {
    @Autowired
    ChatlogMapper mapper;

    @Override
    public List<EspChatlog> getSomeInfoByCaregiver(String no, Integer shoufa) {
        return mapper.getSomeInfoByCaregiver(no,shoufa);
    }

    @Override
    public List<EspChatlog> getSomeInfoByRelatives(String no, Integer shoufa) {
        return mapper.getSomeInfoByRelatives(no,shoufa);
    }

    @Override
    public EspChatlog getOneInfoByID(Integer id) {
        return mapper.getOneInfoByID(id);
    }

    @Override
    public boolean insertinfo(EspChatlog info) {
        return mapper.insertinfo(info);
    }

    @Override
    public boolean deleteinfoByCaregiver(Integer id) {
        return mapper.deleteinfoByCaregiver(id);
    }

    @Override
    public boolean deleteinfoByRelatives(Integer id) {
        return mapper.deleteinfoByRelatives(id);
    }

    @Override
    public boolean deleteinfo(Integer id) {
        return mapper.deleteinfo(id);
    }



  /*  @Override
    public List<EspChatlog> getSomeInfoByCaregivershou(String no) {
        return mapper.getSomeInfoByCaregivershou(no);
    }
    @Override
    public List<EspChatlog> getSomeInfoByCaregiverfa(String no) {
        return mapper.getSomeInfoByCaregiverfa(no);
    }*/
}
