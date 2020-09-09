package com.example.chatroom.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.presenter.RegisterPre;
import com.example.chatroom.R;


public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {
    private EditText nickname,username,password,ensure;
    private Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
    }

    @Override
    protected void runOnProcess(Bundle bundle) {

    }

    @Override
    protected void initView() {
        nickname = findViewById(R.id.registration_nickname);
        password = findViewById(R.id.registration_password);
        ensure = findViewById(R.id.registration_ensure);
        username = findViewById(R.id.registration_username);
        finish = findViewById(R.id.register_finish);
        finish.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.register_lay;
    }

    @Override
    public void register(String nickname,String username,String password,String ensure) {
        UserBean.getInstance().setNickname(nickname);
        UserBean.getInstance().setUsername(username);
        UserBean.getInstance().setPassword(password);
        UserBean.getInstance().setEnsure(ensure);
        new RegisterPre().register(UserBean.getInstance(),this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_finish:
                register(nickname.getText().toString(),username.getText().toString(),password.getText().toString(),ensure.getText().toString());
                break;
        }
    }
    @Override
    public void succeed(String success) {
        showToast(success);
    }


    @Override
    public void failed(String fail) {
        showToast(fail);
    }
}
