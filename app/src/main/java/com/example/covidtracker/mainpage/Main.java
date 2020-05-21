package com.example.covidtracker.mainpage;

import android.net.Uri;
import android.os.Bundle;

import com.example.covidtracker.R;
import com.example.covidtracker.adaptercollection.PageViewAdapter;
import com.example.covidtracker.tabscollection.SearchBar;
import com.example.covidtracker.tabscollection.Universal;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Main extends AppCompatActivity implements Universal.OnFragmentInteractionListener, SearchBar.OnFragmentInteractionListener{

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("COVID DATA");

        final TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Univeral"));
        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.ViewPager);
        final PageViewAdapter adapter = new PageViewAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 1){
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}