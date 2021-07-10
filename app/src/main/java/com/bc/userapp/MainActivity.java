package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bc.userapp.fragments.BookingFragment;
import com.bc.userapp.fragments.HomeFragment;
import com.bc.userapp.fragments.NearByFragments;
import com.bc.userapp.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {


    LinearLayout liappointment,lihome,liprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*-------Status Color Code To Change--------*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        getSupportFragmentManager().beginTransaction().add(R.id.container,new HomeFragment()).commit();

       // getSupportFragmentManager().beginTransaction().add(R.id.container,new NearByFragments()).commit();


        liappointment = findViewById(R.id.liappointment);
        lihome = findViewById(R.id.lihome);
        liprofile = findViewById(R.id.liprofile);


        liprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();

            }
        });

        lihome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

            }
        });


        liappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container,new BookingFragment()).commit();

            }
        });


    }
}