package com.sdm.sergio.mytrack.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentGenres;
import com.sdm.sergio.mytrack.fragment.FragmentProfile;
import com.sdm.sergio.mytrack.fragment.FragmentSpinner;
import com.sdm.sergio.mytrack.task.MovieDiscoverReq;

public class ActBottomNav extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                android.support.v4.app.Fragment context = null;
                if (tabId == R.id.tab_genres) {
                    context = FragmentGenres.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer,context);
                    transaction.commit();
                }
                if (tabId == R.id.tab_trending) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    context = FragmentSpinner.newInstance();
                    transaction.replace(R.id.contentContainer,context);
                    transaction.commit();
                    transaction = getSupportFragmentManager().beginTransaction();
                    MovieDiscoverReq task = new MovieDiscoverReq(transaction);
                    task.execute();

                }
                if (tabId == R.id.tab_profile) {
                    context = FragmentProfile.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer,context);
                    transaction.commit();
                }

            }
        });
    }


}