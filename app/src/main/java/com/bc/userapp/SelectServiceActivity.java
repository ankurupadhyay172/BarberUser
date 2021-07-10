package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bc.userapp.databinding.ActivitySelectServiceBinding;
import com.bc.userapp.models.SalonModel;
import com.bc.userapp.models.SalonService;
import com.bc.userapp.models.ServiceModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SelectServiceActivity extends AppCompatActivity {



    Button btnContinue;

    MyViewModel myViewModel;
    ActivitySelectServiceBinding binding;

    MutableLiveData<List<ServiceModel>> servicelist;

    Gson gson = new Gson();
    SalonModel model;
    List<SalonService> haircut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_select_service);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_select_service);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        btnContinue = findViewById(R.id.btnContinue);

        Intent intent = getIntent();
        if (intent.hasExtra("data"))
        {

            model =gson.fromJson(intent.getStringExtra("data"),SalonModel.class);
            servicelist = myViewModel.getAllServices(model.getId());

            servicelist.observe(this, new Observer<List<ServiceModel>>() {
                @Override
                public void onChanged(List<ServiceModel> serviceModels) {

                    binding.setDatalist(serviceModels.get(0).getHaircut());
                    binding.setShavinglist(serviceModels.get(0).getShave());
                    binding.setHaircolorlist(serviceModels.get(0).getColor());

                    binding.setMassagelist(serviceModels.get(0).getMassage());
                    binding.setThreadlist(serviceModels.get(0).getThreading());

//                    try {
//                        haircut = gson.fromJson(serviceModels.get(0).getHaircut(), new TypeToken<List<ServiceModel>>(){}.getType());
//
//                        strings.add("Select Haircut");
//                        for (ServiceModel model:serviceModels)
//                        {
//                           SalonService[] serviceModels1 = gson.fromJson(model.getHaircut(),SalonService[].class);
//
//                            Log.d("mylogvalue",""+serviceModels1[0].getName());
//                            for (SalonService service:serviceModels1)
//                            {
//                                strings.add(service.getName()+" ( ₹"+service.getPrice()+" )");
//                            }
//
//                        }
//
//
//
//                        binding.setHaircutlist(strings);
//                    }catch (Exception e)
//                    {
//
//                        Toast.makeText(SelectServiceActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//




                }
            });


            binding.setCallback(this);


        }



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectServiceActivity.this,CheckoutActivity.class));
            }
        });



    }


    public void selectHairCut(Spinner haircut)
    {
        if (haircut.getSelectedItemPosition()>0)
        {

            try {
                List<SalonService> datalist = gson.fromJson(binding.getDatalist(), new TypeToken<List<SalonService>>(){}.getType());

                Toast.makeText(this, ""+datalist.get(haircut.getSelectedItemPosition()-1).getName(), Toast.LENGTH_SHORT).show();
            }catch (Exception e)
            {
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
}