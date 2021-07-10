package com.bc.userapp.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.bc.userapp.interfaces.OnComplete;
import com.bc.userapp.models.BarberModel;
import com.bc.userapp.models.SalonModel;
import com.bc.userapp.models.ServiceModel;
import com.bc.userapp.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyFirebase {

    Context context;
    FirebaseFirestore db;




    public MyFirebase(Context context) {
        this.context = context;
        this.db = FirebaseFirestore.getInstance();
    }




    public void getShops(MutableLiveData<List<SalonModel>> list)
    {


        db.collection("Shops").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<SalonModel> list1 = new ArrayList<>();
                for (DocumentSnapshot document:queryDocumentSnapshots)
                {
                    SalonModel model = document.toObject(SalonModel.class);
                    model.setId(document.getId());
                    list1.add(model);
                }
                list.setValue(list1);

            }
        });


    }


    public void getBarber(MutableLiveData<List<BarberModel>> barber,String id)
    {
        db.collection("Shops").document(id).collection("Barber").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<BarberModel> list = new ArrayList<>();
                for (DocumentSnapshot document:queryDocumentSnapshots)
                {
                    BarberModel model = document.toObject(BarberModel.class);
                    model.setId(document.getId());
                    list.add(model);

                }

                barber.setValue(list);
            }
        });

    }

    public void getServices(String id, MutableLiveData<List<ServiceModel>> servicelist)
    {
        db.collection("Shops").document(id).collection("Services").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<ServiceModel> list = new ArrayList<>();
                for (DocumentSnapshot document:queryDocumentSnapshots)
                {
                    ServiceModel model = document.toObject(ServiceModel.class);
                    model.setId(document.getId());
                    list.add(model);
                }
                servicelist.setValue(list);
            }
        });

    }


    public void isRagistered(String mobile,MutableLiveData<Boolean> isRegistred)
    {
        db.collection("Users").document(mobile).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists())
                {
                    isRegistred.setValue(true);
                }
                else
                {
                    isRegistred.setValue(false);
                }




            }
        });

    }


    public void signup(User user, OnComplete onComplete)
    {
        db.collection("Users").document(user.getMobile_no()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                onComplete.onGetComplete(true);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onComplete.onGetComplete(false);
            }
        });
    }


    public void getUser(String mobile,MutableLiveData<User> userMutableLiveData)
    {
        db.collection("Users").document(mobile).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists())
                {
                    User user =documentSnapshot.toObject(User.class);
                    userMutableLiveData.setValue(user);
                }



            }
        });
    }
}
