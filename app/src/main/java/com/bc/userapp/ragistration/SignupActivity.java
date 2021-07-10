package com.bc.userapp.ragistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bc.userapp.Common;
import com.bc.userapp.MainActivity;
import com.bc.userapp.MyViewModel;
import com.bc.userapp.R;
import com.bc.userapp.databinding.ActivitySignupBinding;
import com.bc.userapp.interfaces.OnComplete;
import com.bc.userapp.models.User;
import com.google.firebase.Timestamp;

public class SignupActivity extends AppCompatActivity implements OnComplete{


    MyViewModel myViewModel;
    ActivitySignupBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_signup);

        binding =DataBindingUtil.setContentView(this,R.layout.activity_signup);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra("data"))
        {

            binding.setMobile(intent.getStringExtra("data"));
        }

        binding.setCallback(this);
        binding.setTitle("SignUp");

    }


    public void signup(EditText name, EditText email, RadioGroup radioGroup, ProgressBar progressBar, Button button)
    {

        RadioButton gender;

        if (Common.isEmpty(name)||radioGroup.getCheckedRadioButtonId()==-1)
        {

            if (radioGroup.getCheckedRadioButtonId()==-1)
                Toast.makeText(this, "please select a gender", Toast.LENGTH_SHORT).show();


            if (Common.isEmpty(name))
            name.setError("Please enter name");
        }
        else
        {

            gender = findViewById(radioGroup.getCheckedRadioButtonId());

            progressBar.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
            User user = new User(email.getText().toString(),"na",binding.getMobile(),name.getText().toString(),"na",gender.getText().toString(), Timestamp.now());
            myViewModel.signUp(user,this);

        }


    }

    public void selectImage()
    {
        Toast.makeText(this, "Comming Soon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetComplete(boolean status) {
        if (status)
        {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Sucessfully Login", Toast.LENGTH_SHORT).show();
        }else 
        {
            Toast.makeText(this, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
        }
        
    }
}