//亲属与老人绑定信息表
package com.esp.boot.entity;

import lombok.Data;

@Data
public class EspRelativesoldmanrelative{
    Integer rorId;
    //String oldManSerialNumber;
    EspOldmaninfo oldmaninfo;//老人信息
    //String relativeNumber;
    EspRelativesinfo relativesinfo;//亲属信息
    String relativesrelative;//两人关系



}