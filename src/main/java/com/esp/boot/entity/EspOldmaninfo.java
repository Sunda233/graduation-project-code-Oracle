/**
 * 实体类，老人信息
 */
package com.esp.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspOldmaninfo {
    Integer oldmanId;
    String oldmanSerialnumber;
    String oldmanIdnumber;
    String oldmanName;
    String oldmanSex;
    Integer oldmanHeight;
    Integer oldmanAge;
    String  oldmanCheckintime;
    String  oldmanRoomnumber;
    //String  caregiverEmpNo;
    /*表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错*/
    /*@TableField(exist = false)  //当前属性表中不存在*/
    EspCaregiverinfo caregiverinfo;//看护信息

}
