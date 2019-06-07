package com.cookandroid.interview_cv_app;


public class User {


    public String getUsername() {
        return username;
    }

    public String getEmail() { return email; }

    public String getTel() {
        return tel;
    }

    private String username;
    private String email;
    private String tel;

    public User() {
    }

    public User(String username, String email, String tel) {
        this.username = username;
        this.email = email;
        this.tel = tel;
    }

}
