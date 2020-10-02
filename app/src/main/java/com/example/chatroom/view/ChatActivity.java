package com.example.chatroom.view;

import android.annotation.SuppressLint;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatroom.BaseActivity;
import com.example.chatroom.R;
import com.example.chatroom.adapter.Msg;
import com.example.chatroom.adapter.MyAdapter;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.thread.ReadThread;
import com.example.chatroom.thread.SendThread;
import com.example.chatroom.utils.ConstantUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private EditText send_msg;
    private Button send;
    private List<Msg> msgList = new ArrayList<>();
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView();
        initAdapter();
        initMsg();
    }
    @Override
    protected void runOnProcess(Bundle bundle) {

    }

    @Override
    protected void initView() {
        send = findViewById(R.id.send);
        send_msg = findViewById(R.id.send_msg);
        send.setOnClickListener(this);

    }
    private void initAdapter(){
        recyclerView = findViewById(R.id.recycler_view);
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(msgList);
        recyclerView.setAdapter(adapter);

    }
    private void initMsg(){
        conn();
    }
    @Override
    protected int getContentViewId() {
        return R.layout.chat_lay;
    }

    public void conn(){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    Log.d("TAG", "coon");
                    socket = new Socket(ConstantUtil.ADDRESS, ConstantUtil.CHAT_PORT);
                    printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),true);
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                    new ReadThread(bufferedReader,rec).start();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                if(ReadThread.isBreak){
                    conn();
                }
                JSONObject content = new JSONObject();
                Log.d("TAG", "content "+send_msg.getText().toString());
                if(!send_msg.getText().toString().equals("")){
                    try {
                        content.put("username",UserBean.getInstance().getUsername());
                        content.put("content",send_msg.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new SendThread(printWriter,content.toString()).start();
                    Msg msg1 = new Msg(UserBean.getInstance().getUsername(),send_msg.getText().toString(), Msg.TYPE_SEND);
                    msgList.add(msg1);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    send_msg.setText("");
                }
                break;
            default:break;
        }
    }

    Handler rec = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                String username = null,content = null;
                try {
                    JSONObject jsonObject = new JSONObject(msg.obj.toString());
                    Log.d("TAG", "msg: "+jsonObject);
                    username = jsonObject.getString("username");
                    content = jsonObject.getString("content");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Msg msg1 = new Msg(username,content, Msg.TYPE_RECEIVED);
                msgList.add(msg1);
                adapter.notifyItemInserted(msgList.size() - 1);
                recyclerView.scrollToPosition(msgList.size() - 1);
            }
        }
    };
}
