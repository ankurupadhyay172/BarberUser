package com.bc.userapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bc.userapp.R;
import com.bc.userapp.databinding.ItemPhotolistBinding;
import com.bc.userapp.models.BarberModel;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {

    Context context;
    List<BarberModel> list;


    public PhotosAdapter(Context context, List<BarberModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       // View view = LayoutInflater.from(context).inflate(R.layout.item_photos,parent,false);
//        View view = LayoutInflater.from(context).inflate(R.layout.item_photolist,parent,false);


        ItemPhotolistBinding binding  = ItemPhotolistBinding.inflate(LayoutInflater.from(context),parent,false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.getRoot(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ItemPhotolistBinding binding;

        public MyViewHolder(@NonNull ItemPhotolistBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }


        public void getRoot(BarberModel model)
        {
            binding.setBarber(model);

        }
    }
}
