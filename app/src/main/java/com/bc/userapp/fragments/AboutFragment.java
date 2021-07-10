package com.bc.userapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bc.userapp.MyViewModel;
import com.bc.userapp.R;
import com.bc.userapp.databinding.FragmentAboutBinding;
import com.bc.userapp.models.SalonModel;

public class AboutFragment extends Fragment {

    SalonModel model;

    public AboutFragment(SalonModel salonModel) {
        // Required empty public constructor
        model = salonModel;
    }
    FragmentAboutBinding binding;

    MyViewModel myViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_about, container, false);

        binding = FragmentAboutBinding.inflate(inflater,container,false);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);


        binding.setSalon(model);

        binding.setCallback(this);
        return binding.getRoot();
    }


    public void openDirection()
    {

        SalonModel salon = binding.getSalon();


        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q="+salon.getDirection().getLatitude()+","+salon.getDirection().getLongitude()+salon.getName()));
            startActivity(intent);
        }catch (Exception e)
        {

        }

    }
}