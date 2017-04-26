package com.sdm.sergio.mytrack.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.activity.GenreMovieActivity;
import com.sdm.sergio.mytrack.activity.MovieActivity;
import com.sdm.sergio.mytrack.adapter.GenreGridAdapter;
import com.sdm.sergio.mytrack.model.GenreMovie;
import com.sdm.sergio.mytrack.model.Movie;
import com.sdm.sergio.mytrack.util.Storage;

/**
 * Created by Sergio on 24/04/2017.
 */

public class FragmentGenresGrid extends android.support.v4.app.Fragment {
    private GridView gridView;
    private GenreGridAdapter adaptador;
    public static FragmentGenresGrid newInstance() {
        return new FragmentGenresGrid();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.f_genres_grid, container, false);
        gridView = (GridView) v.findViewById(R.id.grid);
        adaptador = new GenreGridAdapter(Storage.getInstance().getGenre().getGenreMovies(),this.getActivity());
        GridView gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(adaptador);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Pasar posicion en la lista de peliculas de genero a la pantalla de película
                Intent intent = new Intent(getActivity(), GenreMovieActivity.class);
                intent.putExtra("position", position);


                getActivity().startActivity(intent);

            }
        });

        return v;
    }
}