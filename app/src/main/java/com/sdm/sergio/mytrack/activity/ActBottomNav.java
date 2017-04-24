package com.sdm.sergio.mytrack.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentGenres;
import com.sdm.sergio.mytrack.fragment.FragmentProfileWeb;
import com.sdm.sergio.mytrack.fragment.FragmentSpinner;
import com.sdm.sergio.mytrack.task.MovieDiscoverReq;
import com.sdm.sergio.mytrack.util.Storage;

public class ActBottomNav extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_moviet);

        //Inicializamos la BBDD
        Storage.StorageInit(getApplicationContext());

        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);


        //Tab por defecto en el BottomBar
        bottomBar.setDefaultTab(R.id.tab_trending);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                android.support.v4.app.Fragment context = null;
                if (tabId == R.id.tab_genres) {
                    context = FragmentGenres.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer,context);
                    getSupportActionBar().setTitle(R.string.title_generos);

                    transaction.commit();
                }
                if (tabId == R.id.tab_trending) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    context = FragmentSpinner.newInstance();
                    transaction.replace(R.id.contentContainer,context);
                    getSupportActionBar().setTitle(R.string.app_name);


                    transaction.commit();
                    transaction = getSupportFragmentManager().beginTransaction();
                    MovieDiscoverReq task = new MovieDiscoverReq(transaction);
                    task.execute();

                }
                if (tabId == R.id.tab_profile) {
                    context = FragmentProfileWeb.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentContainer,context);
                    //Cambiar título del action bar a Perfil
                    getSupportActionBar().setTitle(R.string.title_profile);
                    transaction.commit();
                }



            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

        // Item Buscar Película
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.setIcon(R.drawable.ic_buscar);

        //Quitar buscar si estoy en el fragment de géneros
        //int idFrag = fragmentoActual.getId();
        /*if(idFrag == getSupportFragmentManager().findFragmentByTag("")){
            searchItem.setVisible(false);
        }
        else{
            searchItem.setVisible(true);
        }*/

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

    /*
    //Esta por ver como tenemos que enviar el género
    public void pasarDatosGeneroGrid(String datos) {//Recibimos el género seleccionado de alguna forma
        android.app.FragmentManager fragmentManager = getFragmentManager();
        DiscoverFragment fragment=(DiscoverFragment) fragmentManager.findFragmentById(R.id.DiscoverFragment);
        DiscoverFragment.//El método que tengo en DiscoverFragment para cambiar el list de películas//;
    }*/


}