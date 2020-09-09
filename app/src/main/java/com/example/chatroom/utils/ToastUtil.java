package com.example.chatroom.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private Context c;
    ToastUtil(Context context){
        c=context;
    }
    public void show(String msg){
        Toast.makeText(c,msg,Toast.LENGTH_SHORT).show();
    }
}
