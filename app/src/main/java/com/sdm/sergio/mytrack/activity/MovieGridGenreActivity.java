package com.sdm.sergio.mytrack.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.adapter.GenreListAdapter;
import com.sdm.sergio.mytrack.adapter.GridMovieAdapter;
import com.sdm.sergio.mytrack.util.Storage;

public class MovieGridGenreActivity extends AppCompatActivity {

    private GridView gridView;
    private GridMovieAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_movie_grid_genre);

        // Toolbar que hace de action Bar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_genre_movies);
        // Cambiar la Toolbar para que haga de ActionBar
        setSupportActionBar(toolbar);

        // Cogemos el genero que nos pasa la pantalla de listView de géneros
        String genero = getIntent().getStringExtra("genero");

        //Poner el titulo del género al toolbar
        getSupportActionBar().setTitle(genero);

    }

    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

        gridView = (GridView) findViewById(R.id.grid);
        adaptador = new GridMovieAdapter(Storage.getInstance().getDiscover(),this);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(adaptador);


        // Item Buscar Película
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.setIcon(R.drawable.ic_buscar);

        //El hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.search_hint));


        //Listener de Buscar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            //Al pulsar intro en el teclado
            public boolean onQueryTextSubmit(String query) {

                //Ocultar el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);

                //Mostrar películas


                return true;
            }

            @Override
            //Que conforme vayas escribiendo haga algo (Puede ser ir haciendo el filtro de películas)
            public boolean onQueryTextChange(String newText) {

                //Acción que hay que hacer
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
