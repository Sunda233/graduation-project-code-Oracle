package com.esp.boot.Msg;

import lombok.Data;

@Data
public class RspMessage {
    public String msgCode;  //2000成功 2001抛出异常 2002 出错,(自己定义)
    public String message;  //提示信息
}
