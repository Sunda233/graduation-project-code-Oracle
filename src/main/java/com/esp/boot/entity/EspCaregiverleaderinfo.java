/**
 * 实体类，看护组长信息
 * 2021年2月19日17:18:00
 */
package com.esp.boot.entity;

import lombok.Data;

/**
 * 看护组长信息实体类
 */
@Data
public class EspCaregiverleaderinfo {
    Integer careleaderinfoid;
    //String caregiverEmpNo;//看护工号
    EspCaregiverinfo caregiverinfo;//看护信息
    String careLeaderInfoPassword;
}
