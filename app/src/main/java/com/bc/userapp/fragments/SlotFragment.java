package com.bc.userapp.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.userapp.R;
import com.bc.userapp.SelectServiceActivity;
import com.bc.userapp.adapters.TablayoutAdapter;
import com.bc.userapp.databinding.FragmentSlotBinding;
import com.bc.userapp.interfaces.OnClickAdapter;
import com.bc.userapp.models.SalonModel;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.Timestamp;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SlotFragment extends Fragment {


    SalonModel model;

    public SlotFragment(SalonModel model) {
        // Required empty public constructor

        this.model = model;
    }

    ViewPager viewPager;
    TabLayout tabLayout;

    Button submit;
    Gson gson = new Gson();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //View view = inflater.inflate(R.layout.fragment_slot, container, false);

        FragmentSlotBinding view = FragmentSlotBinding.inflate(inflater, container, false);


        viewPager = view.getRoot().findViewById(R.id.viewpager);
        tabLayout = view.getRoot().findViewById(R.id.tablayout);
        submit = view.getRoot().findViewById(R.id.submit);

        usingViewPager();




//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SelectServiceActivity.class);
//                intent.putExtra("data",gson.toJson(model));
//
//                startActivity(intent);
//            }
//        });


        Date date = Timestamp.now().toDate();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        String today =format.format(date);

        view.setDate(today);

        view.setCallback(this);

        return view.getRoot();

    }

    private void usingViewPager() {





        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addTab(tabLayout.newTab().setText("MORNING"));
        tabLayout.addTab(tabLayout.newTab().setText("AFTER NOON"));
        tabLayout.addTab(tabLayout.newTab().setText("EVENING"));

        TablayoutAdapter adapter = new TablayoutAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        wrapTabIndicatorToTitle(tabLayout, 50, 50);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }






    public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                tabView.setMinimumWidth(0);
                // set padding to 0 for wrapping indicator as title
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                // setting custom margin between tabs
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        // left
                        settingMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {
                        // right
                        settingMargin(layoutParams, internalMargin, externalMargin);
                    } else {
                        // internal
                        settingMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }



    private void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }



    public void selectDate(TextView date)
    {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), datePickerListener, mYear, mMonth, mDay);
        dateDialog.show();

        dateDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


        long now = System.currentTimeMillis() - 1000;
        dateDialog.getDatePicker().setMaxDate(now+(1000*60*60*24*7));




    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateYouChoosed = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

            Toast.makeText(getActivity(), ""+dateYouChoosed, Toast.LENGTH_SHORT).show();
//                    getRecycle1Data();
//                    tvDate.setText(dateYouChoosed );
        }
    };

}