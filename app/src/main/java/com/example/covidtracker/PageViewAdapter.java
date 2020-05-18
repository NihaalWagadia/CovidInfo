package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageViewAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PageViewAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm, NumberOfTabs);
        this.mNoOfTabs = NumberOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Universal universal = new Universal();
                return universal;


            case 1:
                SearchBar searchBar = new SearchBar();
                return searchBar;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
