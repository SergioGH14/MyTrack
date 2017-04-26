package com.sdm.sergio.mytrack.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentSpinner;
import com.sdm.sergio.mytrack.task.MovieSearchReq;

/**
 * Created by Sergio on 26/04/2017.
 */

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        setContentView(R.layout.a_movie_grid_genre);
        String query = getIntent().getStringExtra("query");
        getSupportActionBar().setTitle(getString(R.string.title_bar_search)+query);
        FragmentSpinner context = FragmentSpinner.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.genre_container,context);
        transaction.commit();
        transaction = getSupportFragmentManager().beginTransaction();
        MovieSearchReq task = new MovieSearchReq(transaction,query);
        task.execute();

    }
}
