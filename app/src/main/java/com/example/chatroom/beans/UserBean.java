package com.example.chatroom.beans;

public class UserBean {
    private String username;
    private String password;
    private String nickname;
    private String ensure;
    private String msg;
    public UserBean(){

    }
    public UserBean(String username,String msg){
        this.username = username;
        this.msg = msg;
    }
    private static UserBean userBean = new UserBean();
    public static UserBean getInstance(){
        return userBean;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }
    public void setEnsure(String ensure){
        this.ensure = ensure;
    }
    public String getEnsure(){
        return ensure;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
