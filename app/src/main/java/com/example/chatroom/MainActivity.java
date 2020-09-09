package com.example.chatroom;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.chatroom.View.ChatActivity;
import com.example.chatroom.View.ForgetActivity;
import com.example.chatroom.View.RegisterActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
                IntentActivity(this, ChatActivity.class);
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
