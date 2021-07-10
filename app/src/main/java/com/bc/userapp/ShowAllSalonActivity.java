package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bc.userapp.databinding.ActivityShowAllSalonBinding;
import com.bc.userapp.models.SalonModel;

import java.util.List;

public class ShowAllSalonActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    ActivityShowAllSalonBinding binding;

    MutableLiveData<List<SalonModel>> getsalon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_all_salon);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_all_salon);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        binding.setTitle("All Salon");

        getsalon = myViewModel.getSalonList();
        getsalon.observe(this, new Observer<List<SalonModel>>() {
            @Override
            public void onChanged(List<SalonModel> list) {

                binding.setShoplist(list);
            }
        });




    }
}