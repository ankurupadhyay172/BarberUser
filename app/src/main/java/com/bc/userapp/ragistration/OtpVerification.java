package com.bc.userapp.ragistration;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerification {


    String code;
    private FirebaseAuth mAuth;
    String verificationID;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallback;

    Activity context;

    OnVerficationComplete onVerficationComplete;
    EditText edtotp;
    ProgressBar oprogressBar;
    TextView error;
    Button submit_otp;


    public OtpVerification(Activity context, OnVerficationComplete onVerficationComplete) {
        this.onVerficationComplete = onVerficationComplete;
        this.context = context;
    }

    public void sendOtp(final Activity context, String mobile, ProgressBar progressBar, LinearLayout li_mobile, LinearLayout li_otp, EditText otp, TextView error, Button submit_mobile)
    {
        this.context = context;
        edtotp = otp;

        mAuth = FirebaseAuth.getInstance();
        //LoadingClass loadingClass = new LoadingClass();
        //final Dialog test = loadingClass.showLoginDialog(context);


        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Otp sent successfully", Toast.LENGTH_SHORT).show();
                // test.dismiss();
                Log.d("mytestdata","send otp successfully");
                verificationID = s;



                li_mobile.setVisibility(View.GONE);
                li_otp.setVisibility(View.VISIBLE);


            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                code = phoneAuthCredential.getSmsCode();

                signInWithCredential(phoneAuthCredential);
                if (code != null) {

                    verifyCode(code,onVerficationComplete);

                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                onVerficationComplete.getVerficationStatus(e.getMessage());
                progressBar.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                error.setText(e.getMessage());
                submit_mobile.setVisibility(View.VISIBLE);

            }
        };










        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(context)                 // Activity (for callback binding)
                        .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);














    }

    public void verifyCode(String code,OnVerficationComplete onVerficationComplete)
    {


        this.onVerficationComplete = onVerficationComplete;
        //oprogressBar.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,code);
        signInWithCredential(credential);


    }
    private void  signInWithCredential(PhoneAuthCredential credential) {


        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                //    oprogressBar.setVisibility(View.GONE);
                    onVerficationComplete.getVerficationStatus("Successfull");







                }else {

                    onVerficationComplete.getVerficationStatus("Please Enter a Valid Otp");
//                    edtotp.setError("Enter a valid otp");
//                    oprogressBar.setVisibility(View.GONE);
//                    Toast.makeText(context, "Not Valid"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });


    }






    public interface OnVerficationComplete
    {
        public void getVerficationStatus(String status);
    }

}
