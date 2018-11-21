package com.yf.sysuser.entity;

/**
 * @Package com.yf.sysuser.entity
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/11 15:04
 */
public class UserEntiy {
    private String userName;
    private String userNick;
    private int age;
    private String password;

    public UserEntiy(String userName, String userNick, int age, String password) {
        this.userName = userName;
        this.userNick = userNick;
        this.age = age;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
