package com.example.chatroom.thread;

import com.example.chatroom.utils.MyInputStreamReader;

import java.io.*;

public class SendThread extends Thread{
    private PrintWriter printWriter;
    private String content;
    public SendThread(PrintWriter printWriter,String content) {
        this.printWriter = printWriter;
        this.content = content;
    }
    @Override
    public void run(){
        printWriter.println(content);
    }
}
