package com.bc.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.bc.userapp.ragistration.LoginActivity;
import com.bc.userapp.ragistration.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    MyViewModel myViewModel;
    MutableLiveData<Boolean> isRagistred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        hideSystemUI();




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                // Intent i = new Intent(SplashScreenActivity.this, SignupActivity.class);


                if (mAuth.getCurrentUser()==null)
                {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);finish();
                }
                else
                {
                    isRagistred = myViewModel.isAvailable(mAuth.getCurrentUser().getPhoneNumber());

                    isRagistred.observe(SplashScreenActivity.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {

                            if (aBoolean)
                            {

                                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                                startActivity(i);finish();
                            }
                            else
                            {

                                Intent i = new Intent(SplashScreenActivity.this, SignupActivity.class);
                                i.putExtra("data",mAuth.getCurrentUser().getPhoneNumber());
                                startActivity(i);

                            }


                        }
                    });


                }

//                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
//                startActivity(i);finish();

            }

        }, 3*1000); // wait for 3 seconds

    }


    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}