package com.example.chatroom.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;

public class ChatActivity extends BaseActivity {
    private EditText send_msg;
    private Button send;
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
        send = findViewById(R.id.send);
        send_msg = findViewById(R.id.send_msg);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.chat_lay;
    }
}
