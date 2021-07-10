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

import com.bc.userapp.R;
import com.bc.userapp.adapters.SelectSlotAdapter;
import com.bc.userapp.interfaces.FragmentToActivity;
import com.bc.userapp.interfaces.OnClickAdapter;
import com.bc.userapp.models.ListModel;

import java.util.ArrayList;

public class EveningSlot extends Fragment implements OnClickAdapter {


    public EveningSlot() {
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
        //View  view = inflater.inflate(R.layout.fragment_evening_slot, container, false);

        View view = inflater.inflate(R.layout.fragment_all, container, false);


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager1);


        listModels = new ArrayList<>();
        listModels.add(new ListModel("04:00 PM ",1));
        listModels.add(new ListModel("04:30 PM ",2));
        listModels.add(new ListModel("05:00 PM ",3));
        listModels.add(new ListModel("05:30 PM ",4));
        listModels.add(new ListModel("06:00 PM ",5));
        listModels.add(new ListModel("06:30 PM ",6));
        listModels.add(new ListModel("07:00 PM ",7));
        listModels.add(new ListModel("07:30 PM ",8));



        adapter = new SelectSlotAdapter(getActivity(),listModels,this);
        recyclerView.setAdapter(adapter);



        return view;
    }
    @Override
    public void onClickAdapter(ListModel model) {
        fragmentToActivity.communication(model);


    }
}