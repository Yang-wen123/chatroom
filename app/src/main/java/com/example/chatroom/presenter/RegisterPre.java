package com.example.chatroom.presenter;

import com.example.chatroom.beans.UserBean;
import com.example.chatroom.model.Re_Mod;
import com.example.chatroom.view.RegisterView;

public class RegisterPre implements RegisterInter {


    @Override
    public void register(UserBean userBean, RegisterView registerView) {
        new Re_Mod().do_check(userBean,registerView);
    }
}
