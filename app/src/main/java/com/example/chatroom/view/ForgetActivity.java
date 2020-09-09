package com.example.chatroom.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.utils.ConstantUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    private TextView search,show;
    private EditText username;
    private String temp;
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
        username = findViewById(R.id.forget_username);
        search = findViewById(R.id.search_password);
        show = findViewById(R.id.show_password);
        search.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.forget_lay;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_password:
                if (username.getText().toString() != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DataInputStream dis = null;
                            DataOutputStream dos = null;
                            try {
                                //阻塞函数，正常连接后才会向下继续执行
                                Socket socket = new Socket(ConstantUtil.ADDRESS, ConstantUtil.PORT);
                                dis = new DataInputStream(socket.getInputStream());
                                dos = new DataOutputStream(socket.getOutputStream());
                                //向服务器写数据
                                dos.writeUTF(username.getText().toString());
                                temp = dis.readUTF();
                            } catch (IOException e) {
                                Log.e("Himi", "Stream error!");
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (dis != null)
                                        dis.close();
                                    if (dos != null)
                                        dos.close();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }).start();

                }else {
                    showToast("输入为空，无法查询！");
                }
                break;
        }
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //响应布局内容
            show.setText(temp);
        }
    };
}