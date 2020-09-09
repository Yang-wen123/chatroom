package com.example.chatroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chatroom.R;
import com.example.chatroom.beans.UserBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<UserBean> user_list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,msg;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.chat_user);
            msg = view.findViewById(R.id.chat_msg);
        }

    }

    public MyAdapter(List<UserBean> user_list) {
        user_list = user_list;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserBean userBean = user_list.get(position);
        holder.username.setText(userBean.getNickname());
        holder.msg.setText(userBean.getMsg());
    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }
}