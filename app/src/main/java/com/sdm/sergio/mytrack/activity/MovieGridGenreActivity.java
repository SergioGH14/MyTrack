package com.sdm.sergio.mytrack.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.adapter.GridMovieAdapter;
import com.sdm.sergio.mytrack.fragment.FragmentSpinner;
import com.sdm.sergio.mytrack.task.MovieGenresReq;

public class MovieGridGenreActivity extends AppCompatActivity {

    private GridView gridView;
    private GridMovieAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getStringExtra("generos");
        setContentView(R.layout.a_movie_grid_genre);
        android.support.v4.app.Fragment context = FragmentSpinner.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.genre_container,context);
        transaction.commit();
        transaction = getSupportFragmentManager().beginTransaction();
        MovieGenresReq task = new MovieGenresReq(transaction,id);
        task.execute();



        //Flecha de Back de la action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Color a la Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        // Cogemos el genero que nos pasa la pantalla de listView de géneros
        String genero = getIntent().getStringExtra("genero");

        //Poner el titulo del género al toolbar
        getSupportActionBar().setTitle(genero);

        //Mostrar ActionBar
        getSupportActionBar().show();

    }

    //Flecha Baeck action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

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
