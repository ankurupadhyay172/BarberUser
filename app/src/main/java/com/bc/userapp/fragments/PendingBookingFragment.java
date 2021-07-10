package com.bc.userapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.userapp.R;
import com.bc.userapp.adapters.MyBookingAdapter;

public class PendingBookingFragment extends Fragment {


    public PendingBookingFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;

    MyBookingAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_booking, container, false);


        recyclerView = view.findViewById(R.id.rvCancel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyBookingAdapter(getActivity());
        recyclerView.setAdapter(adapter);




        return view;
    }
}