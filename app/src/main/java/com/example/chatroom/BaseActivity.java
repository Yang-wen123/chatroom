package com.example.chatroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(getContentViewId());
        initView();
        runOnProcess(savedInstanceState);
    }

    /**
     * 全局返回Context参数
     *
     * @return
     */
    protected static Context getContext() {
        return context;
    }

    protected abstract void runOnProcess(Bundle bundle);

    protected abstract void initView();

    /**
     * 抽象方法返回Layout布局
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 全局的整型数Toast工具
     *
     * @param number 整形数
     */
    protected void showToast(int number) {
        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
    }

    /**
     * 全局的字符串Toast工具
     *
     * @param msg 消息主体
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 全局的Activity跳转工具
     *
     * @param context,cls
     */
    protected void IntentActivity(Context context,Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }
}
