package com.example.chatroom.thread;

import android.os.Message;
import com.example.chatroom.adapter.Msg;
import com.example.chatroom.utils.ConstantUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import android.os.Handler;

public class ReadThread extends Thread {
    private BufferedReader bufferedReader;
    private Handler handler;
    public static boolean isBreak = false;
    public ReadThread(BufferedReader bufferedReader, Handler handler) {
        this.bufferedReader = bufferedReader;
        this.handler = handler;
    }

    public void run() {
        while(!isBreak) {
            try {
                String content = bufferedReader.readLine();
                Message message = new Message();
                message.what = 0x123;
                message.obj = content;
                handler.sendMessage(message);
                //System.out.println(readLine);
            } catch (IOException var2) {
                isBreak = true;
                System.out.println("服务器关闭，请稍后重连");
            }
        }

    }
}
