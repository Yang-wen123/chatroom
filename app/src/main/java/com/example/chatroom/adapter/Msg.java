package com.example.chatroom.adapter;

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String nickname;
    private String content;
    private int type;

    public Msg(String nickname,String content, int type) {
        this.nickname = nickname;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public String getNickname(){
        return nickname;
    }
}
