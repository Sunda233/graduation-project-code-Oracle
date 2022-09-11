/**
 * 看护人员信息
 */
package com.esp.boot.entity;

import lombok.Data;

@Data
public class EspCaregiverinfo {
    Integer caregiverId;
    String caregiverEmpno;
    String caregiverPhone;
    String caregiverName;
    Integer caregiverAge;
    String caregiverSex;
    Integer caregiverHeight;
    String caregiverIdnumber;
    String caregiverIntheyear;
}
