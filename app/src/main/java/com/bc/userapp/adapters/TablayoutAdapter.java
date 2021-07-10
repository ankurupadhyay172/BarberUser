package com.bc.userapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bc.userapp.fragments.AfterNoonFragment;
import com.bc.userapp.fragments.All;
import com.bc.userapp.fragments.EveningSlot;

public class TablayoutAdapter extends FragmentStatePagerAdapter {
    int mnooftabs;


    public TablayoutAdapter(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }

    public TablayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:

                All tab1 = new All();
                return tab1;


            case 1:
                AfterNoonFragment tab2 = new AfterNoonFragment();
                return tab2;


            case 2:
                EveningSlot tab3 = new EveningSlot();
                return tab3;
              

            default:
                return null;
        }







    }

    @Override
    public int getCount() {
        return 3;
    }
}
