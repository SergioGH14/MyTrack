package com.sdm.sergio.mytrack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.adapter.GenreListAdapter;


public class FragmentGenres extends android.support.v4.app.Fragment{

    public static FragmentGenres newInstance(){
        FragmentGenres fragment = new FragmentGenres();
        return new FragmentGenres();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);}
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
        ListView listView = (ListView) v.findViewById(R.id.custom_listview_genres);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //Cambiar título del action bar por el género
                getActivity().setTitle(itemGenreList[position]);

                /*
                //Enviar a DiscoverFragment el género
                getActivity().pasarDatosGeneroGrid(itemGenreList[position])
                */

                //Cambiar al fragment con el grid de generos según el género

            }
        });


        return v;


    }
}

