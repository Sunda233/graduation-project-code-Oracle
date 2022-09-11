/**
 * 家属基本信息表
 */
package com.esp.boot.entity;

import lombok.Data;

@Data
public class EspRelativesinfo {
    Integer relativesId;
    String relativesSerialnumber;
    String relativesName;
    String relativesPhone;
    String relativesPassword;
}
