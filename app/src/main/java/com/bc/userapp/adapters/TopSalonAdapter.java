package com.bc.userapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bc.userapp.BR;
import com.bc.userapp.R;
import com.bc.userapp.SubCategoryActivity;
import com.bc.userapp.databinding.ItemPopularsalonBinding;
import com.bc.userapp.databinding.ItemSalon2Binding;
import com.bc.userapp.models.SalonModel;
import com.google.gson.Gson;

import java.util.List;

public class TopSalonAdapter extends RecyclerView.Adapter<TopSalonAdapter.MyViewHolder> {

    Context context;
    List<SalonModel> list;
    int layout=0;

    Gson gson = new Gson();
    public TopSalonAdapter(Context context, List<SalonModel> list) {
        this.context = context;
        this.list = list;
    }

    public TopSalonAdapter(Context context, List<SalonModel> list,int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getItemViewType(int position) {
        if (layout==0)
            return 0;
        else
            return 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view =LayoutInflater.from(context).inflate(R.layout.item_popularsalon,parent,false);

        if (viewType==0)
        {
            ViewDataBinding inflate = ItemPopularsalonBinding.inflate(LayoutInflater.from(context), parent, false);
            return new MyViewHolder(inflate);
        }
        else
        {
            ViewDataBinding inflate = ItemSalon2Binding.inflate(LayoutInflater.from(context), parent, false);
            return new MyViewHolder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.getRoot(list.get(position));




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SubCategoryActivity.class);
                intent.putExtra("data",gson.toJson(list.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ViewDataBinding binding;

        public MyViewHolder(@NonNull ViewDataBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void getRoot(SalonModel model)
        {
            binding.setVariable(BR.salon,model);

        }
    }
}
