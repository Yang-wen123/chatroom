package com.example.chatroom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.thread.LoginThread;
import com.example.chatroom.utils.ConstantUtil;
import com.example.chatroom.view.ChatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private TextView username,password;
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
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
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
        switch (item.getItemId()){
            case R.id.action_settings:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;
            case R.id.token:


                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                JSONObject login = new JSONObject();
                try {
                    login.put("username",username.getText().toString());
                    login.put("token",password.getText().toString());
                    login(login.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:break;
        }
    }
    public void login(final String content){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(ConstantUtil.ADDRESS,ConstantUtil.LOGIN_PORT);
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                    new LoginThread(bufferedReader,printWriter,content,loginHandler).start();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler loginHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x456) {
                String username,result;
                int code;
                try {
                    JSONObject jsonObject = new JSONObject(msg.obj.toString());
                    Log.d("TAG", "msglogin: "+msg.obj.toString());
                    username = jsonObject.getString("username");
                    result = jsonObject.getString("result");
                    code = jsonObject.getInt("code");
                    switch (code){
                        case 200:
                            showToast(result);
                            UserBean.getInstance().setUsername(username);
                            IntentActivity(MainActivity.this,ChatActivity.class);
                            break;
                        case 202:
                            showToast(result);
                            break;
                        default:break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
