package com.bc.userapp.models;

import com.google.firebase.Timestamp;

public class User {
    String email,image,mobile_no,name,token,gender;
    Timestamp timestamp;

    public User() {
    }

    public User(String email, String image, String mobile_no, String name, String token, String gender, Timestamp timestamp) {
        this.email = email;
        this.image = image;
        this.mobile_no = mobile_no;
        this.name = name;
        this.token = token;
        this.gender = gender;
        this.timestamp = timestamp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
