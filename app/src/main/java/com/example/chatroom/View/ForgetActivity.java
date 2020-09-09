package com.example.chatroom.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;

public class ForgetActivity extends BaseActivity {
    private TextView search;
    private EditText username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
    }
    @Override
    protected void runOnProcess(Bundle bundle) {

    }

    @Override
    protected void initView() {
        username = findViewById(R.id.forget_username);
        search = findViewById(R.id.search_password);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.forget_lay;
    }
}
