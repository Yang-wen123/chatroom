package com.example.chatroom.Beans;

public class UserBean {
    private String username;
    private String password;
    private String nickname;
    private String ensure;
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

}
