package com.bc.userapp.ragistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.userapp.Common;
import com.bc.userapp.MainActivity;
import com.bc.userapp.MyViewModel;
import com.bc.userapp.R;
import com.bc.userapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements OtpVerification.OnVerficationComplete {


    EditText mobile_no,otp;
    ActivityLoginBinding binding;
    OtpVerification otpVerification;
    ProgressBar progressBar;
    Button submit;
    MyViewModel myViewModel;

    MutableLiveData<Boolean> isregistred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);



        otp = findViewById(R.id.otp);
        mobile_no = findViewById(R.id.mobile_no);
        otpVerification = new OtpVerification(this,this);




        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length()==6)
                {

                    InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);


                    //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mobile_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().length()==10)
                {

                    InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);


                    //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });



        binding.setCallback(this);


    }


    public void sendOtp(EditText mobile_no, ProgressBar progressBar, LinearLayout li_mobile, LinearLayout li_otp, EditText otp, TextView error, Button submit)
    {
        if (Common.isEmpty(mobile_no))
        {
            mobile_no.setError("Please enter mobile no");
        }
        else
        {

            submit.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            otpVerification.sendOtp(this,mobile_no.getText().toString(),progressBar,li_mobile,li_otp,otp,error,submit);
        }


    }




    public void verifyOtp(EditText otp,ProgressBar progressBar,Button submit)
    {
        if (Common.isEmpty(otp))
            otp.setError("Please enter valid otp");
        else
        {
            this.progressBar = progressBar;
            this.submit = submit;
            submit.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            otpVerification.verifyCode(otp.getText().toString(),this);
        }
    }

    @Override
    public void getVerficationStatus(String status) {


        if (status.equals("Successfull"))
        {
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();

            isregistred =myViewModel.isAvailable("+91"+mobile_no.getText().toString());

            if (isregistred!=null)
            {
                isregistred.observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean)
                        {
//                            Toast.makeText(LoginActivity.this, "User is ragistred", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }else
                        {
                            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                            intent.putExtra("data","+91"+mobile_no.getText().toString());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            //Toast.makeText(LoginActivity.this, "User is unragistred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }
        else
        {
            progressBar.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
            Toast.makeText(this, ""+status, Toast.LENGTH_SHORT).show();
        }
    }
}