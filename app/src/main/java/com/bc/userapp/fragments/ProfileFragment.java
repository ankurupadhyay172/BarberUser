package com.bc.userapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.userapp.AboutUsActivity;
import com.bc.userapp.EditProfileActivity;
import com.bc.userapp.ManageNotificationActivity;
import com.bc.userapp.R;
import com.bc.userapp.contact.ContactUsActivity;
import com.bc.userapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_profile, container, false);

        FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater,container,false);


        binding.setCallback(this);


        return binding.getRoot();
    }


    public void rating()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=https://play.google.com/store/apps/details?id=com.bc.userapp"));
        startActivity(intent);
    }


    public void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Your Friend  Invite you on Barber Connect Please download from below link \nhttps://play.google.com/store/apps/details?id=com.bc.userapp");
        startActivity(Intent.createChooser(intent,"Select Target to share"));

    }


    public void aboutUs()
    {
        Intent intent = new Intent(getActivity(), AboutUsActivity.class);

        startActivity(intent);

    }


    public void contactus()
    {
        Intent intent = new Intent(getActivity(), ContactUsActivity.class);

        startActivity(intent);


    }


    public void editprofile()
    {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);

        startActivity(intent);


    }

    public void openNotification()
    {
        Intent intent = new Intent(getActivity(), ManageNotificationActivity.class);

        startActivity(intent);

    }
}