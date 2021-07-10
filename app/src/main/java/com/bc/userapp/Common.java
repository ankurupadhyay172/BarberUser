package com.bc.userapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bc.userapp.adapters.PhotosAdapter;
import com.bc.userapp.adapters.TopSalonAdapter;
import com.bc.userapp.models.BarberModel;
import com.bc.userapp.models.SalonModel;
import com.bc.userapp.models.SalonService;
import com.bc.userapp.models.ServiceModel;
import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Common {



    @BindingAdapter("setbarber")
    public static void setBarberAdapter(RecyclerView recyclerView, List<BarberModel> list)
    {

        if (list==null)
            return;
        if (list.isEmpty())
            return;

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        PhotosAdapter photoListAdapter = new PhotosAdapter(recyclerView.getContext(),list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(photoListAdapter);


    }

    @BindingAdapter("setsalon")
    public static void setSalon(RecyclerView recyclerView, List<SalonModel> list)
    {



        if (list==null)
            return;

        if (list.isEmpty())
            return;

        TopSalonAdapter adapter = new TopSalonAdapter(recyclerView.getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);


    }






    @BindingAdapter("setsalonvertical")
    public static void setSalonVertical(RecyclerView recyclerView, List<SalonModel> list)
    {



        if (list==null)
            return;

        if (list.isEmpty())
            return;

        TopSalonAdapter adapter = new TopSalonAdapter(recyclerView.getContext(),list,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);


    }






    @BindingAdapter("setimage")
    public static void setImage(ImageView image, String url)
    {
        if (url!=null)
        {

            Glide.with(image.getContext()).load(url).placeholder(R.drawable.slogo).into(image);

        }


    }



    @BindingAdapter("onback")
    public static void setOnBack(ImageView back,String data)
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)back.getContext()).finish();
            }
        });

    }



    public static void whatsAppContact(Context context, String contact) {
        //String contact = "+917726046262"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + contact;
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    @BindingAdapter("setservice")
    public static void setService(Spinner spinner,String data)
    {
        Gson gson = new Gson();
        List<SalonService> datalist;
        List<String> list = new ArrayList<>();
        list.add("Select Service");

        if (data==null)
            return;


        datalist = gson.fromJson(data, new TypeToken<List<SalonService>>(){}.getType());


        for (SalonService model:datalist)
        {
            list.add(model.getName()+" ₹"+model.getPrice());

        }



        ArrayAdapter adapter = new ArrayAdapter(spinner.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);


    }



    public static boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
