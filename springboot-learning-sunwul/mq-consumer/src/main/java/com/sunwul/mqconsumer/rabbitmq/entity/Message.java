package com.sunwul.mqconsumer.rabbitmq.entity;

/*****
 * @author: Sunwul
 * @date: 2020/2/28 16:56
 * PS: 信息实体封装  此处暂时未用到
 * 此实体为探索MQ序列化与反序列所加_sunuwl
 */
public class Message {
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
