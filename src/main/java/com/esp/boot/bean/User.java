package com.esp.boot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//全有参构造器
@NoArgsConstructor//全无参构造器
@Data
public class User {
    /**
     * 所有属性都应该在数据库中
     */
    /*表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错*/
    @TableField(exist = false)  //当前属性表中不存在
    private String username;

    private  String password;
}
