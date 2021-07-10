package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bc.userapp.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about_us);

        ActivityAboutUsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_about_us);

        binding.setTitle("About us");



    }
}