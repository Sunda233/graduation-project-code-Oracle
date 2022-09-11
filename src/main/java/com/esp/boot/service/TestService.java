package com.esp.boot.service;


import com.esp.boot.entity.EspRelativesinfo;


public interface TestService {

    EspRelativesinfo getinfoById(Integer id);


    public void saveEspRelativesInfo(EspRelativesinfo espRelativesInfo) ;
}
