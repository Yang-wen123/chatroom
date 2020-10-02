package com.example.chatroom.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatroom.R;
import com.example.chatroom.beans.UserBean;
import com.example.chatroom.utils.ConstantUtil;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private  List<Msg> msgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView receive_user,send_user;
        TextView receive_msg,send_msg;
        LinearLayout receive_layout,send_layout;
        public ViewHolder (View view)
        {
            super(view);
            receive_layout = itemView.findViewById(R.id.receive_layout);
            send_layout = itemView.findViewById(R.id.send_layout);
            receive_user = itemView.findViewById(R.id.receive_user);
            send_user = itemView.findViewById(R.id.send_user);
            receive_msg = itemView.findViewById(R.id.receive_msg);
            send_msg = itemView.findViewById(R.id.send_msg);
        }

    }

    public  MyAdapter (List<Msg> msgList){
        this.msgList = msgList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        Msg msg = msgList.get(position);
        if (msg.getType() == ConstantUtil.TYPE_REC) {
            viewHolder.receive_layout.setVisibility(View.VISIBLE);
            viewHolder.send_layout.setVisibility(View.GONE);
            viewHolder.receive_msg.setText(msg.getContent());
            viewHolder.receive_msg.setTextIsSelectable(true);
            Log.d("width", "onBindViewHolder: "+viewHolder.receive_msg.getText().length());
            if(viewHolder.receive_msg.getText().length()>28){
                viewHolder.receive_msg.setWidth(600);
            }
            viewHolder.receive_user.setText(msg.getUsername());
        } else if (msg.getType() == ConstantUtil.TYPE_SEND) {
            viewHolder.send_layout.setVisibility(View.VISIBLE);
            viewHolder.receive_layout.setVisibility(View.GONE);
            viewHolder.send_msg.setText(msg.getContent());
            viewHolder.send_msg.setTextIsSelectable(true);
            Log.d("width", "onBindViewHolder: "+viewHolder.send_msg.getText().length());
            if(viewHolder.send_msg.getText().length()>28){
                viewHolder.send_msg.setWidth(600);
            }
            viewHolder.send_user.setText(msg.getUsername());
        }
    }

    @Override
    public int getItemCount(){
        return msgList.size();
    }
}