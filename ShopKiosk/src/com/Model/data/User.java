package com.Model.data;

public class User {
    String username;
    String pasword;

    public User(String username, String pasword) {
        this.username = username;
        this.pasword = pasword;
    }

    public String getUsername() {
        return username;
    }

    public String getPasword() {
        return pasword;
    }
}
