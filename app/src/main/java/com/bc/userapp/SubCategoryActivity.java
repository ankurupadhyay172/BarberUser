package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bc.userapp.adapters.HeightWrappingViewPager;
import com.bc.userapp.adapters.PhotosAdapter;
import com.bc.userapp.adapters.UserDetailTabAdapter;
import com.bc.userapp.databinding.ActivitySubCategoryBinding;
import com.bc.userapp.interfaces.FragmentToActivity;
import com.bc.userapp.interfaces.OnClickAdapter;
import com.bc.userapp.models.BarberModel;
import com.bc.userapp.models.ListModel;
import com.bc.userapp.models.SalonModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements FragmentToActivity {

    HeightWrappingViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    Gson gson = new Gson();
    ActivitySubCategoryBinding binding;
    SalonModel model;

    MyViewModel myViewModel;

    MutableLiveData<List<BarberModel>> barberlist;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sub_category);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sub_category);
        Intent intent = getIntent();

        if (intent.hasExtra("data"))
        {

            model = gson.fromJson(intent.getStringExtra("data"),SalonModel.class);
            binding.setSalon(model);



        }

        recyclerView = findViewById(R.id.recyclerview);




        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);



        barberlist = myViewModel.getBarberlist(binding.getSalon().getId());
        barberlist.observe(this, new Observer<List<BarberModel>>() {
            @Override
            public void onChanged(List<BarberModel> barberModels) {


                binding.setBarberlist(barberModels);

            }
        });







        submit = findViewById(R.id.submit);
        tabLayout = findViewById(R.id.tabCoupon);
        viewPager = findViewById(R.id.vpCoupon);
        tabLayout.setTabIndicatorFullWidth(true);

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Booking");
        tabLayout.addTab(firstTab);
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("About");
        tabLayout.addTab(secondTab);
//        TabLayout.Tab thiredTab = tabLayout.newTab();
//        thiredTab.setText("Gallery");
//        tabLayout.addTab(thiredTab);
//        TabLayout.Tab fourthTab = tabLayout.newTab();
//        fourthTab.setText("Review");
//        tabLayout.addTab(fourthTab);

        UserDetailTabAdapter adapter = new UserDetailTabAdapter(getSupportFragmentManager(),binding.getSalon());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        binding.setCallback(this);
    }



    public void onWebsite()
    {
        Toast.makeText(this, "Commin Soon", Toast.LENGTH_SHORT).show();
    }


    public void openWhatsapp()
    {

        Common.whatsAppContact(this,"+91"+binding.getSalon().getWhatsapp());
    }



    public void shareApp()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
       // intent.putExtra(Intent.EXTRA_TEXT, model.getShare_link());
        startActivity(Intent.createChooser(intent, "Select Target to share"));

    }


    public void openMapLocation()
    {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q="+model.getDirection().getLatitude()+","+model.getDirection().getLongitude()+" (Barber Shop)"));
        startActivity(intent);

    }



    public void submit()
    {
                Intent intent = new Intent(this, SelectServiceActivity.class);
                intent.putExtra("data",gson.toJson(model));

                startActivity(intent);
    }
    @Override
    public void communication(ListModel model2) {
    //    submit.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, SelectServiceActivity.class);
        intent.putExtra("data",gson.toJson(model));
        intent.putExtra("slot",gson.toJson(model2));

        startActivity(intent);

    }
}