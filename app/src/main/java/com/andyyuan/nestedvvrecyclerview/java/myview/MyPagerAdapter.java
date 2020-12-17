package com.andyyuan.nestedvvrecyclerview.java.myview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author : AndyYuan
 * date   : 2020/12/7 000718:05
 * desc   :
 * version: 2.0
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public List<String> titles;
    public List<MyFragment> myFragments;

    public MyPagerAdapter(FragmentManager fm, List<String> titles, List<MyFragment> myFragments) {
        super(fm);
        this.titles = titles;
        this.myFragments = myFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return  myFragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
