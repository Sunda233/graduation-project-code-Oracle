/**
 * 看护组信息
 */
package com.esp.boot.entity;

import lombok.Data;

@Data
public class EspGroupInfo {
    //组号
    Integer groupId;
    //组名作用
    String groupName;
    //组长工号作用
    EspCaregiverinfo caregiverinfo;//看护人员信息


}
