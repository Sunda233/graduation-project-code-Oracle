/**
 * 身体数据表
 */
package com.esp.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class EspElderlyPhysicalData {
   Integer physicaldataId;
   //String oldManSerialNumber;
   EspOldmaninfo oldmanInfo;//老人信息
   //String caregiverEmpNo;
  // EspCaregiverinfo caregiverinfo;//看护信息,从老人信息中取
   String physicaldataBloodpressurelow;
   String physicaldataBloodpressurehigh;
   String physicaldataWeight;
   String physicaldataDiet;
   String physicaldataDate;
   String physicaldataRemark;
   @TableField(exist = false)  //当前属性表中不存在
   public EspCaregivergroup group;//显示组信息
   @TableField(exist = false)  //当前属性表中不存在
   public EspRelativesoldmanrelative relativesoldmanrelative ;//显示亲属关系信息
}
