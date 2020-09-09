package com.example.chatroom.presenter;

import com.example.chatroom.beans.UserBean;
import com.example.chatroom.view.RegisterView;

public interface RegisterInter {

    void register(UserBean userBean, RegisterView registerView);
}
