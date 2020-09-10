package com.example.chatroom.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;
import com.example.chatroom.adapter.Msg;
import com.example.chatroom.adapter.MyAdapter;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.utils.ClientThread;
import com.example.chatroom.utils.ConstantUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private EditText send_msg;
    private Button send;
    private List<Msg> msgList = new ArrayList<>();
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private Handler handler;
    private ClientThread clientThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
        initAdapter();
        initMsg();
    }
    @Override
    protected void runOnProcess(Bundle bundle) {

    }

    @Override
    protected void initView() {
        send = findViewById(R.id.send);
        send_msg = findViewById(R.id.send_msg);
        send.setOnClickListener(this);
    }
    private void initAdapter(){
        recyclerView = findViewById(R.id.recycler_view);
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(msgList);
        recyclerView.setAdapter(adapter);

    }
    private void initMsg(){
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    Msg msg1 = new Msg(msg.obj.toString(), Msg.TYPE_RECEIVED);
                    msgList.add(msg1);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size() - 1);
                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();
    }
    @Override
    protected int getContentViewId() {
        return R.layout.chat_lay;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                String s = send_msg.getText().toString();
                if (!s.equals("") && s != null) {
                    Msg msg = new Msg(s, Msg.TYPE_SEND);
                    msgList.add(msg);
                    Message message = new Message();
                    message.what = 0x345;
                    message.obj = s;
                    clientThread.receiveHandler.sendMessage(message);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    send_msg.setText("");
                }
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            showToast( "再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}
