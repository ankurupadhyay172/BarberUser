package com.bc.userapp.models;

import com.google.firebase.firestore.GeoPoint;

public class SalonModel {

    String id,name,address,city,state,image,whatsapp,pincode,about_salon;
    boolean block,isopen;
    float rating;
    String mon_fri_open,sat_sun_open;



    public String getMon_fri_open() {
        return mon_fri_open;
    }

    public void setMon_fri_open(String mon_fri_open) {
        this.mon_fri_open = mon_fri_open;
    }

    public String getSat_sun_open() {
        return sat_sun_open;
    }

    public void setSat_sun_open(String sat_sun_open) {
        this.sat_sun_open = sat_sun_open;
    }

    public String getAbout_salon() {
        return about_salon;
    }

    public void setAbout_salon(String about_salon) {
        this.about_salon = about_salon;
    }

    GeoPoint direction;

    public GeoPoint getDirection() {
        return direction;
    }

    public void setDirection(GeoPoint direction) {
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
