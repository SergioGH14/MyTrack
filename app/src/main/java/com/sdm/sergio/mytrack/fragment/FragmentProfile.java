package com.sdm.sergio.mytrack.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.activity.ProfileActibity;
import com.sdm.sergio.mytrack.activity.RegistreActivity;

/**
 * Created by Sergio on 20/04/2017.
 */

public class FragmentProfile extends android.support.v4.app.Fragment {

    public static FragmentProfile newInstance() {
        return new FragmentProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_profile, container, false);

        Button b_regis = (Button) view.findViewById(R.id.registre_b);
        b_regis.setOnClickListener(clickListener);
        Button b_per = (Button) view.findViewById(R.id.perfil_b);
        b_per.setOnClickListener(clickListener);

        return view;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.registre_b:
                    Intent registre = new Intent(getActivity(), RegistreActivity.class);
                    startActivity(registre);
                    break;
                case R.id.perfil_b:
                    Intent profile = new Intent(getActivity(), ProfileActibity.class);
                    startActivity(profile);
                    break;
            }
        }
    };
}


