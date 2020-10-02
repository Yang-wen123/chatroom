package com.example.chatroom.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
    private Context context;
    public DialogUtil(Context context){
        this.context = context;
    }

    public void tokenDialog(Context context,String token){
        AlertDialog.Builder tokenDialog=new AlertDialog.Builder(context);
        tokenDialog.setTitle("Token")
                .setMessage(token)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }
}
