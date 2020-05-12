package com.jsflzhong.test2_noactivity.entity;

public class Msg {

    //消息的类型:收到的消息
    public static final int TYPE_RECEIVED = 0;
    //消息的类型:发送的消息
    public static final int TYPE_SENT = 1;

    //消息的内容
    private String content;
    //消息的类型
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
