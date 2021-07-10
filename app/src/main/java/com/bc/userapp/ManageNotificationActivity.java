package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

public class ManageNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_manage_notification);

        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_notification);



    }
}