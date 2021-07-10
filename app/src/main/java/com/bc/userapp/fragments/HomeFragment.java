package com.bc.userapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bc.userapp.Common;
import com.bc.userapp.MyViewModel;
import com.bc.userapp.R;
import com.bc.userapp.ShowAllSalonActivity;
import com.bc.userapp.adapters.CategoryAdapter;
import com.bc.userapp.adapters.OffersAdapter;
import com.bc.userapp.adapters.TopSalonAdapter;
import com.bc.userapp.databinding.FragmentHomeBinding;
import com.bc.userapp.models.SalonModel;
import com.bc.userapp.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView top_services,top_salon,offers;
    Integer ivcategory[] = {R.drawable.ic_group_9, R.drawable.ic_group_4576, R.drawable.ic_group_4573};
    String tvcategory[] = {"Salon", "Spa", "Beauty Parler"};
    CategoryAdapter adapter;

    MutableLiveData<List<SalonModel>> salonlist;

    MutableLiveData<User> getusers;
    MyViewModel myViewModel;

    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_home, container, false);

        FragmentHomeBinding view = FragmentHomeBinding.inflate(inflater, container, false);
        MyViewModel model = ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        mAuth = FirebaseAuth.getInstance();

        offers = view.getRoot().findViewById(R.id.offers);
        top_services = view.getRoot().findViewById(R.id.top_services);
        top_salon = view.getRoot().findViewById(R.id.top_salon);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        top_services.setLayoutManager(mLayoutManager1);
        top_services.setItemAnimator(new DefaultItemAnimator());

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), Arrays.asList(ivcategory),Arrays.asList(tvcategory));
        top_services.setAdapter(adapter);




        OffersAdapter adapter2 = new OffersAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        offers.setLayoutManager(mLayoutManager2);
        offers.setItemAnimator(new DefaultItemAnimator());
        offers.setAdapter(adapter2);


        salonlist = model.getSalonList();
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);


        salonlist.observe(getActivity(), new Observer<List<SalonModel>>() {
            @Override
            public void onChanged(List<SalonModel> salonModels) {

                view.setShoplist(salonModels);



            }
        });


        if (mAuth.getCurrentUser()!=null)
        {
            getusers = myViewModel.getUser(mAuth.getCurrentUser().getPhoneNumber());

            getusers.observe(getActivity(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    view.setUser(user);
                }
            });

        }





        view.setCallback(this);

        return view.getRoot();


    }


    public void openAllSalon()
    {
        startActivity(new Intent(getActivity(), ShowAllSalonActivity.class));
    }


    public void openWhatsApp()
    {
        Common.whatsAppContact(getActivity(),"+919636964321");
    }
}