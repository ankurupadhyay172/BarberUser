package com.bc.userapp;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bc.userapp.firebase.MyFirebase;
import com.bc.userapp.interfaces.OnComplete;
import com.bc.userapp.models.BarberModel;
import com.bc.userapp.models.SalonModel;
import com.bc.userapp.models.ServiceModel;
import com.bc.userapp.models.User;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    MutableLiveData<List<SalonModel>> salonlist;
    MyFirebase myFirebase;

    MutableLiveData<List<BarberModel>> barberlist;

    MutableLiveData<List<ServiceModel>> servicelist;

    MutableLiveData<Boolean> isregistred;


    MutableLiveData<User> getUsers;



    public MyViewModel(@NonNull Application application) {
        super(application);
        myFirebase = new MyFirebase(application);

    }




    public MutableLiveData<List<SalonModel>> getSalonList()
    {

        if (salonlist==null)
        {
            salonlist = new MutableLiveData<>();
            myFirebase.getShops(salonlist);
        }
        return salonlist;


    }


    public MutableLiveData<List<BarberModel>> getBarberlist(String id)
    {
        if (barberlist==null)
        {
            barberlist = new MutableLiveData<>();
            myFirebase.getBarber(barberlist,id);
        }
        return barberlist;

    }


    public MutableLiveData<List<ServiceModel>> getAllServices(String id)
    {

        if (servicelist==null)
        {
            servicelist = new MutableLiveData<>();
            myFirebase.getServices(id,servicelist);

        }

        return servicelist;

    }



    public MutableLiveData<Boolean> isAvailable(String mobile)
    {
        if (isregistred==null)
        {
            isregistred = new MutableLiveData<>();
            myFirebase.isRagistered(mobile,isregistred);
        }
        return isregistred;
    }



    public void signUp(User user, OnComplete onComplete)
    {
        myFirebase.signup(user,onComplete);

    }


    public MutableLiveData<User> getUser(String mobile)
    {
        if (getUsers==null)
        {
            getUsers = new MutableLiveData<>();
            myFirebase.getUser(mobile,getUsers);
        }
        return getUsers;
    }


}
