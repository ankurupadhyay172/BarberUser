package com.bc.userapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bc.userapp.R;
import com.bc.userapp.adapters.SelectSlotAdapter;
import com.bc.userapp.interfaces.FragmentToActivity;
import com.bc.userapp.interfaces.OnClickAdapter;
import com.bc.userapp.models.ListModel;

import java.util.ArrayList;
import java.util.List;


public class All extends Fragment implements OnClickAdapter {


    public All() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    SelectSlotAdapter adapter;
    ArrayList<ListModel> listModels;
    FragmentToActivity fragmentToActivity;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentToActivity)
        {
            fragmentToActivity = (FragmentToActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager1);


        listModels = new ArrayList<>();
        listModels.add(new ListModel("08:00 AM ",1));
        listModels.add(new ListModel("08:30 AM ",2));
        listModels.add(new ListModel("09:00 AM ",3));
        listModels.add(new ListModel("09:30 AM ",4));
        listModels.add(new ListModel("10:00 AM ",5));
        listModels.add(new ListModel("10:30 AM ",6));
        listModels.add(new ListModel("11:00 AM ",7));
        listModels.add(new ListModel("11:30 AM ",8));



        adapter = new SelectSlotAdapter(getActivity(),listModels,this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClickAdapter(ListModel model) {

        fragmentToActivity.communication(model);

    }
}