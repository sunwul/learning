package com.sunwul.mq.rabbitmq.entity;

import java.io.Serializable;

/*****
 * @author: Sunwul
 * @date: 2020/2/28 16:56
 * PS: 信息实体封装
 * 关于序列化接口Serializable
 * Serializable序列化接口没有任何方法或者字段,只是用于标识可序列化的语义
 * 实现了Serializable接口的类可以被ObjectOutputStream转换为字节流
 * 同时也可以通过ObjectInputStream再将其解析为对象
 * 例如:我们可以将序列化对象写入文件后,再次从文件中读取它并反序列化成对象
 *     也就是说,可以使用表示对象及其数据的类型信息和字节在内存中重新创建对象
 */
public class Message implements Serializable {
    /**
     * messageId  id
     * messageData  内容
     * createTime  创建时间
     * */
    private String messageId;
    private String messageData;
    private String createTime;

    public Message(){}
    public Message(String messageId, String messageData, String createTime) {
        this.messageId = messageId;
        this.messageData = messageData;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", messageData='" + messageData + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
