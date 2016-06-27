package com.poc;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

;

/**
 * Created by aalishan on 12/1/16.
 */

class MyPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragment;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragment = fragments;
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }


    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }


    @Override
    public float getPageWidth(int position) {
        return 0.88f;
    }


    @Override
    public Fragment getItem(int position) {
        //fragment=mFragment.get(position);
        return mFragment.get(position);
    }


}