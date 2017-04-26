package com.sdm.sergio.mytrack.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.activity.MovieActivity;
import com.sdm.sergio.mytrack.adapter.GridMovieAdapter;
import com.sdm.sergio.mytrack.model.Movie;
import com.sdm.sergio.mytrack.util.Storage;

/*
 * Created by Sergio on 19/04/2017.
 */

public class FragmentTrending extends android.support.v4.app.Fragment{
    private GridView gridView;
    private GridMovieAdapter adaptador;

    public static FragmentTrending newInstance(){
        return new FragmentTrending();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.f_trending, container, false);
        gridView = (GridView) v.findViewById(R.id.grid);
        adaptador = new GridMovieAdapter(Storage.getInstance().getDiscover(),this.getActivity());
        gridView.setAdapter(adaptador);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Objeto con la Información de la película, se cambiará por lo que tengamos de Trakt
                Movie item = (Movie) parent.getItemAtPosition(position);

                //Pasar el título y la imagen a la pantalla de película
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra("id", item.getIds().getTmdb().toString());


                getActivity().startActivity(intent);

            }
        });

        return v;
    }
    }

