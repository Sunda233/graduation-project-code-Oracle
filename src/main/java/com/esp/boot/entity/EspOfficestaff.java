/**
 * 办公人员信息，独立与看护存在
 */
package com.esp.boot.entity;

import lombok.Data;

@Data
public class EspOfficestaff {
    Integer officestaffId;
    String officestaffEmpno;
    //EspCaregiverInfo caregiverInfo;
    String officestaffPassword;
}
