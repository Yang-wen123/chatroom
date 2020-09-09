package com.example.chatroom.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;
import com.example.chatroom.adapter.MyAdapter;
import com.example.chatroom.beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private EditText send_msg;
    private Button send;
    private List<UserBean> userBean = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
    }
    @Override
    protected void runOnProcess(Bundle bundle) {

    }

    @Override
    protected void initView() {
        send = findViewById(R.id.send);
        send_msg = findViewById(R.id.send_msg);
        send.setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(userBean);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.chat_lay;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                UserBean userBean1 = new UserBean();
                userBean.add(new UserBean(userBean1.getNickname(),send_msg.getText().toString()));
                initView();
                break;
        }
    }
}
