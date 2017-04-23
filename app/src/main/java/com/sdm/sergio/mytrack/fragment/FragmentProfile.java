package com.sdm.sergio.mytrack.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdm.sergio.mytrack.R;

/**
 * Created by Sergio on 20/04/2017.
 */

public class FragmentProfile extends android.support.v4.app.Fragment{

    public static FragmentProfile newInstance(){return new FragmentProfile();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_profile, null);
    }
}

