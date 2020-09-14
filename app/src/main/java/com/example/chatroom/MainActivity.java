package com.example.chatroom;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.example.chatroom.adapter.Msg;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.utils.ConstantUtil;
import com.example.chatroom.view.ChatActivity;
import com.example.chatroom.view.ForgetActivity;
import com.example.chatroom.view.RegisterActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private TextView username,password,forget,register;
    private Button login;
    private Toolbar toolbar;
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        username = findViewById(R.id.username);
        username.setOnClickListener(this);
        password = findViewById(R.id.password);
        password.setOnClickListener(this);
        forget = findViewById(R.id.forget);
        forget.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                showToast("登陆中");
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
                            dos.writeUTF(username.getText().toString()+","+password.getText().toString());
                            String[] res = dis.readUTF().split(",");
                            if(res[0].equals("200")){
                                UserBean.getInstance().setUsername(username.getText().toString());
                                UserBean.getInstance().setNickname(res[1]);
                                IntentActivity(MainActivity.this, ChatActivity.class);
                            }else {
                                Looper.prepare();
                                showToast(dis.readUTF());
                                Looper.loop();
                            }
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
                    }
                }).start();

                break;
            case R.id.register:
                IntentActivity(this, RegisterActivity.class);
                break;
            case R.id.forget:
                IntentActivity(this, ForgetActivity.class);
                break;
        }
    }
}
