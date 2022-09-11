/**
 * 处理全局异常
 * 处理整个web的异常
 * */
package com.esp.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice/*系统启动加载会分析注解*/
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})  //处理异常，代表当前是异常处理器，处理数学运算异常以及空指针异常
    public String handleArithException(Exception e){
        log.error("异常是：{}",e);/*记录异常信息*/
        return "login"; //视图地址
    }
}
