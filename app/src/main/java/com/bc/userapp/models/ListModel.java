package com.bc.userapp.models;

public class ListModel {

    String text_list;
    boolean isbooked;
    int slot_no;

    public ListModel(String text_list,  int slot_no) {
        this.text_list = text_list;
        this.slot_no = slot_no;
    }

    public int getSlot_no() {
        return slot_no;
    }

    public void setSlot_no(int slot_no) {
        this.slot_no = slot_no;
    }

    public ListModel() {
    }

    public String getText_list() {
        return text_list;
    }

    public void setText_list(String text_list) {
        this.text_list = text_list;
    }

    public boolean isIsbooked() {
        return isbooked;
    }

    public void setIsbooked(boolean isbooked) {
        this.isbooked = isbooked;
    }
}
