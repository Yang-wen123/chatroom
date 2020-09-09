package com.example.chatroom.Presenter;

import com.example.chatroom.Beans.UserBean;
import com.example.chatroom.Model.Re_Mod;
import com.example.chatroom.View.RegisterView;

public class RegisterPre implements RegisterInter {


    @Override
    public void register(UserBean userBean, RegisterView registerView) {
        new Re_Mod().do_check(userBean,registerView);
    }
}
