package com.example.chatroom.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.utils.ConstantUtil;
import com.example.chatroom.view.RegisterView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Re_Mod implements Re_Mod_Inter {
    private Socket socket;
    @Override
    public void do_check(final UserBean userBean, final RegisterView registerView) {
        String pass = userBean.getPassword();
        String ensure = userBean.getEnsure();
        try{
            if(pass!=null&&ensure!=null&&pass.equals(ensure)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataInputStream dis = null;
                        DataOutputStream dos = null;
                        try {
                            //阻塞函数，正常连接后才会向下继续执行
                            socket = new Socket(ConstantUtil.ADDRESS, ConstantUtil.PORT);
                            dis = new DataInputStream(socket.getInputStream());
                            dos = new DataOutputStream(socket.getOutputStream());
                            //向服务器写数据
                            dos.writeUTF(userBean.getNickname()+","+userBean.getUsername()+","+userBean.getPassword());
                            String temp = dis.readUTF();
                            Looper.prepare();
                            registerView.succeed(temp);
                            Looper.loop();
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
            }else if(pass!=null&&ensure!=null&&!pass.equals(ensure)){
                registerView.failed("密码不一致，注册失败！");
            }
        }catch (Exception e){
            registerView.failed("输入错误，注册失败！");
        }

    }

}
