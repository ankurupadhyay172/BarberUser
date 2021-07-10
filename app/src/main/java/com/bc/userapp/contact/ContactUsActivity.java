package com.bc.userapp.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bc.userapp.R;
import com.bc.userapp.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_us);

        ActivityContactUsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_us);
        binding.setTitle("Contact Us");



    }
}