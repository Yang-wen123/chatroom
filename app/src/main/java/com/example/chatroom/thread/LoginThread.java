package com.example.chatroom.thread;

import android.os.Handler;
import android.os.Message;
import com.example.chatroom.utils.MyInputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LoginThread extends Thread {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String content;
    private Handler handler;
    public LoginThread(BufferedReader bufferedReader, PrintWriter printWriter, String content, Handler handler){
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
        this.content = content;
        this.handler = handler;
    }
    @Override
    public void run(){
        printWriter.println(content);
        try {
            Message message = new Message();
            message.what = 0x456;
            message.obj = bufferedReader.readLine();
            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
