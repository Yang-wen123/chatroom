package com.example.chatroom.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientThread implements Runnable {
    private Socket socket = null;
    private Handler sendHandler;
    public Handler receiveHandler;
    BufferedReader bufferedReader = null;
    BufferedOutputStream bufferedOutputStream = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    public ClientThread(Handler handler) {
        this.sendHandler = handler;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ConstantUtil.ADDRESS, 10000);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String content;
                    try {
                        while ((content = dis.readUTF()) != null) {
                            Message message = new Message();
                            message.what = 0x123;
                            message.obj = content;
                            sendHandler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Looper.prepare();
            receiveHandler = new Handler() {
                @Override
                public void handleMessage(Message message) {
                    if (message.what == 0x345) {
                        try {
                            dos.writeUTF(message.obj.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e) {
            System.out.println("网络连接请求超时!!!");
        } catch (SocketException e) {
            System.out.println("连接服务器失败!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
