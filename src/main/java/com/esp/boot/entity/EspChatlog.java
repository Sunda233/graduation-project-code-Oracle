/**
 * 留言记录表
 */
package com.esp.boot.entity;

import lombok.Data;

/**
 * 收发消息
 */
@Data
public class EspChatlog {
   Integer chatlogId;
   //Integer chatlogSenderid;
   EspCaregiverinfo caregiverinfo;
   // Integer chatlogReceiverid;
   EspRelativesinfo relativesinfo;
   String  chatlogSenddate;
   String chatlogSendtime;
   String chatlogSendcontent;
   String chatlogRemark;
   String chatlogSource;//发送源头 1 亲属发给看护者  2看护者发给亲属
   Integer chatlogState;//邮件状态  1为均可见 ，2为只有亲属可见。3为只有看护者可见 4为能删除状态
}