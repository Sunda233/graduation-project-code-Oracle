/**
 * 看望预约表
 */
package com.esp.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 亲属预约单
 */
@Data
public class EspVisitappointment {
    Integer visitappointmentId;
    String visitappointmentDate;
    String visitappointmentCreatedate;
    String visitappointmentRemark;
    //String relativePhone;
    EspRelativesinfo relativesinfo;//亲属信息
    //String oldManIdNumber;
    EspOldmaninfo oldmaninfo;//老人信息
    String visitappointmentState;

    @TableField(exist = false)  //当前属性表中不存在
    String ror;//显示亲属关系
}
