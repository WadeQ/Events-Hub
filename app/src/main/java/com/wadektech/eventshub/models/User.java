package com.wadektech.eventshub.models;


public class User {
    private String userId ;
    private String username ;
    private String phoneNumber ;

    public User(String username, String phoneNumber, String userId) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.userId = userId ;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
