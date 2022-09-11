package com.esp.boot.service;

import com.esp.boot.entity.EspCaregiverleaderinfo;
import com.esp.boot.entity.EspOfficestaff;
import com.esp.boot.entity.EspRelativesinfo;

public interface IndexService {
    /**
     * 查询办公人员账号信息
     */
    public EspOfficestaff selectStaff(String username, String password);
    /**
     * 查询看护组长账号信息
     */
    public EspCaregiverleaderinfo selectCaregiver(String username, String password);
    /**
     * 查询亲属账号信息
     */
    public EspRelativesinfo selectRelatives(String username, String password);
    /**
     * 增加亲属信息（注册功能）
     */
    public boolean insert(EspRelativesinfo espRelativesinfo);
}
