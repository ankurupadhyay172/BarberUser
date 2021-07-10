package com.bc.userapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bc.userapp.R;
import com.bc.userapp.SubCategoryActivity;
import com.bc.userapp.databinding.ItemSelectBinding;
import com.bc.userapp.interfaces.OnClickAdapter;
import com.bc.userapp.models.ListModel;

import java.util.List;

public class SelectSlotAdapter extends RecyclerView.Adapter<SelectSlotAdapter.MyViewHolder> {

    Activity context;
    List<ListModel> list;
    int myPos = -1;
    OnClickAdapter onClickAdapter;

    public SelectSlotAdapter(Activity context, List<ListModel> list,OnClickAdapter onClickAdapter) {
        this.context = context;
        this.list = list;
        this.onClickAdapter = onClickAdapter;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View view = LayoutInflater.from(context).inflate(R.layout.item_select,parent,false);


        ItemSelectBinding binding = ItemSelectBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.getRoot(list.get(position));


        holder.tvslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                myPos = position;
                notifyDataSetChanged();
                onClickAdapter.onClickAdapter(list.get(position));


            }
        });

//        ListModel model = list.get(position);
//        holder.tvSelectTime.setText(model.getText_list());
//
        if (myPos == position) {
            holder.tvslot.setBackgroundResource(R.drawable.rectangle_gradient_multi);
            holder.tvslot.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tvslot.setBackgroundResource(R.drawable.rectangle_smoke_gray);
            holder.tvslot.setTextColor(Color.parseColor("#666666"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvslot;
        ItemSelectBinding binding;

        public MyViewHolder(@NonNull ItemSelectBinding itemView) {
            super(itemView.getRoot());
            // tvSelectTime = itemView.findViewById(R.id.tvSelectTime);
            tvslot = itemView.getRoot().findViewById(R.id.tvslot);
            binding = itemView;
        }


        public void getRoot(ListModel model) {

            binding.setList(model);



        }


    }

    public void onhandleClick(TextView textView) {


        textView.setBackgroundResource(R.drawable.rect_white);
        textView.setTextColor(Color.parseColor("#000000"));






        textView.setBackgroundResource(R.drawable.rectangle_gradient_multi);
        textView.setTextColor(Color.parseColor("#ffffff"));

    }
}
