package com.sdm.sergio.mytrack.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.activity.MovieActivity;
import com.sdm.sergio.mytrack.activity.MovieGridGenreActivity;
import com.sdm.sergio.mytrack.adapter.GenreListAdapter;


public class FragmentGenres extends android.support.v4.app.Fragment{

    public static FragmentGenres newInstance(){
        FragmentGenres fragment = new FragmentGenres();
        return new FragmentGenres();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Si este fragmento quiere participar al recibir una llamada el onCreateOptionsMenu de
            // la actividad principal.
            setHasOptionsMenu(false);
    }

    //Listas de Generos y sus iconos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_genres, null);

        final String itemGenreList[] = {
                getString(R.string.accion),
                getString(R.string.aventura),
                getString(R.string.animacion),
                getString(R.string.comedia),
                getString(R.string.crimen),
                getString(R.string.documental),
                getString(R.string.drama),
                getString(R.string.fantasia),
                getString(R.string.historia),
                getString(R.string.miedo),
                getString(R.string.musical),
                getString(R.string.misterio),
                getString(R.string.romance),
                getString(R.string.ficcion),
                getString(R.string.thriller),
                getString(R.string.guerra),
                getString(R.string.oeste)
        };



        Integer itemImageList[] = {

                R.drawable.ic_accion,R.drawable.ic_aventuras,R.drawable.ic_animacion,R.drawable.ic_comedia,
                R.drawable.ic_crimen,R.drawable.ic_documental,R.drawable.ic_drama,
                R.drawable.ic_fantasia,
                R.drawable.ic_historia,
                R.drawable.ic_miedo,
                R.drawable.ic_musical,
                R.drawable.ic_misterio,
                R.drawable.ic_romance,
                R.drawable.ic_ficcion,
                R.drawable.ic_thriller,
                R.drawable.ic_guerra,
                R.drawable.ic_oeste
        };


        GenreListAdapter adapter = new GenreListAdapter(getActivity(), itemImageList, itemGenreList);
        final ListView listView = (ListView) v.findViewById(R.id.custom_listview_genres);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Para que cuando pulses en un género no se note ningún color, salía amarillo
                listView.setBackgroundColor(getResources().getColor(R.color.colorIcons));

                //Cambiar título del action bar por el género
                getActivity().setTitle(itemGenreList[position]);

                String genero = itemGenreList[position];

                //Pasar el género a la pantalla de grid de películas por género
                Intent intent = new Intent(getActivity(), MovieGridGenreActivity.class);
                intent.putExtra("genero", genero);

                getActivity().startActivity(intent);

            }
        });


        return v;


    }
}

