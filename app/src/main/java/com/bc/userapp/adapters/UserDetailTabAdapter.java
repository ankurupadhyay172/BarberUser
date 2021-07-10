package com.bc.userapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bc.userapp.fragments.AboutFragment;
import com.bc.userapp.fragments.SlotFragment;
import com.bc.userapp.models.SalonModel;

public class UserDetailTabAdapter extends FragmentStatePagerAdapter {

    SalonModel model;


    public UserDetailTabAdapter(@NonNull FragmentManager fm,SalonModel salonModel) {
        super(fm);
        model = salonModel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SlotFragment tab1 = new SlotFragment(model);
                return tab1;
            case 1:
                AboutFragment tab2 = new AboutFragment(model);

                return tab2;
//            case 2:
//                GalleryFragment tab3 = new GalleryFragment();
//                return tab3;
//            case 3:
//                ReviewsFragment tab4 = new ReviewsFragment();
//                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
