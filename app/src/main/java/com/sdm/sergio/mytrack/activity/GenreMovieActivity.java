package com.sdm.sergio.mytrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionMenu;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentGenres;
import com.sdm.sergio.mytrack.model.Genre;
import com.sdm.sergio.mytrack.model.GenreMovie;
import com.sdm.sergio.mytrack.model.ProductionCountry;
import com.sdm.sergio.mytrack.model.SpokenLanguage;
import com.sdm.sergio.mytrack.model.TMDBMovie;
import com.sdm.sergio.mytrack.util.Storage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by colo on 26/04/2017.
 */

public class GenreMovieActivity extends AppCompatActivity {

    private GenreMovie movie;
    private HashMap<String,String> genres = new HashMap<String, String>();

    // Floating Buttons
    private FloatingActionMenu menuAdd;
    private com.github.clans.fab.FloatingActionButton fabtrailer;
    private com.github.clans.fab.FloatingActionButton fab1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_genre_movie);

        //Quitar Action Bar
        getSupportActionBar().hide();

        // Cogemos la pelicula mediante la clase Storage gracias al id del Intent
        int position = getIntent().getIntExtra("position",0);
        movie = Storage.getInstance().getGenre().getGenreMovies().get(position);
        //Rellenar datos de la película
        rellenarDatos();

        //Floating Action Menu y Buttons
        menuAdd = (FloatingActionMenu) findViewById(R.id.menu_add);

        //Botón Añadir a favoritos
        fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(clickListener);

        //Botón Tráiler
        fabtrailer = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabtrailer);
        fabtrailer.setOnClickListener(clickListener);

        //Mostrar el menú flotante
        menuAdd.showMenuButton(true);

        menuAdd.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAdd.toggle(true);
            }
        });

        //Flecha de Atrás
        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Que vuelva atrás
                onBackPressed();
            }
        });

    }




    //Rellenar datos película
    public void rellenarDatos(){

        //TÍTULO
        TextView nombreMovie = (TextView) findViewById(R.id.title);
        TextView tv_titulo = (TextView) findViewById(R.id.title);
        tv_titulo.setText(movie.getTitle());
        nombreMovie.setText(movie.getTitle());

        //SINOPSIS
        TextView tv_sinopsis = (TextView) findViewById(R.id.tv_sinopsis);
        tv_sinopsis.setText(movie.getOverview());

        //Poner la imagen "pequeña"
        ImageView imagenMovie = (ImageView) findViewById(R.id.image);
        Glide.with(imagenMovie.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath())
                .into(imagenMovie);

        //Poner la imagen "grande"
        ImageView imagenMovieBack = (ImageView) findViewById(R.id.imageback);
        Glide.with(imagenMovie.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+movie.getBackdropPath())
                .into(imagenMovieBack);

        //TMDBM nota
        TextView tv_tmdb = (TextView) findViewById(R.id.tv_tmdb);
        double tmdb_nota = movie.getVoteAverage();
        tv_tmdb.setText(""+tmdb_nota);

        //FECHA DE ESTRENO
        TextView tv_estreno = (TextView) findViewById(R.id.tv_estreno);
        tv_estreno.setText(movie.getReleaseDate());

        //Si es una pelicula de adultos poner +18
        if(movie.isAdult()){
            TextView tv_adulto = (TextView) findViewById(R.id.tv_adulto);
            tv_adulto.setText(getString(R.string.mayor));
        }

    }

    //Acciones de los botones flotantes
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
                    //Añadir a la colección personal
                    //Ocultar el menú
                    menuAdd.toggle(false);
                    break;

                case R.id.fabtrailer:

                    //Abrir trailer en youtube
                    if(movie.isVideo()){

                        //Cojo url del video
                        String videoUrl = "https://www.youtube.com/watch?v=FbvPuv6Amjg";

                        //Lanzar el intent para que se abra en youtube o internet
                        String action = Intent.ACTION_VIEW;
                        Uri uri = Uri.parse("https://www.youtube.com/watch?v=FbvPuv6Amjg");
                        Intent videoIntent = new Intent(action, uri);
                        startActivity(videoIntent);

                    }
                    else {
                        Toast.makeText(GenreMovieActivity.this, getString(R.string.no_trailer), Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };
}
