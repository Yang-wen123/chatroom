package com.example.chatroom.view;

public interface RegisterView {
    void register(String nickname,String username,String password,String ensure);
    void succeed(String success);
    void failed(String fail);
}
