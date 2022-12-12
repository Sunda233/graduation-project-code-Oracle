package com.esp.boot.entity;
/*测试类*/
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class emp {
    Integer empno;
    String ename;
    String job;
    Integer mgr;
    Date hiredate;
    Integer sal;
    Integer comm;
    Integer deptno;
}


