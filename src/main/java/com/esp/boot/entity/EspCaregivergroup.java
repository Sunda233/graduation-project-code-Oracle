/**
 * 看护分组信息
 */
package com.esp.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class EspCaregivergroup {
    Integer caregiverGroupid;
    //String caregiverEmpNo;//看护工号
    EspCaregiverinfo caregiverinfo;
    Integer groupId;

    @TableField(exist = false)  //当前属性表中不存在
    public EspOldmaninfo oldmaninfo;

}
