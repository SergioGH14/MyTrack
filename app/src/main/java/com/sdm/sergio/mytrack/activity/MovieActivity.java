package com.sdm.sergio.mytrack.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.model.TMDBMovie;
import com.sdm.sergio.mytrack.util.Storage;

public class MovieActivity extends AppCompatActivity {

    private TMDBMovie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_fullmovie);
    // Cogemos la pelicula mediante la clase Storage gracias al id del Intent
        String id = getIntent().getStringExtra("id");
        movie = Storage.getInstance().extractFullMovie(id);
        //Rellenar datos de la película
        rellenarDatos();
        //Mostrar Home en Action bar y quitar el título
        /*getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        getSupportActionBar().hide();}

    //Rellenar datos película
    public void rellenarDatos(){

        //TÍTULO
        TextView nombreMovie = (TextView) findViewById(R.id.title);
        TextView tv_titulo = (TextView) findViewById(R.id.tv_title);
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
        /*
        //PAÍSES
        TextView tv_paises = (TextView) findViewById(R.id.tv_paises);
        String paisesList = "";

        List<ProductionCountry> paises = movie.getProductionCountries();
        for (ProductionCountry pais: paises) {

            paisesList = paisesList +", "+pais.getName();
        }

        tv_paises.setText(paisesList);

        //GENEROS
        TextView tv_generos = (TextView) findViewById(R.id.tv_genero);

        String genresList = "";

        List<Genre> genres = movie.getGenres();
        for (Genre genre: genres) {

            genresList = genresList +", "+genre.getName();
        }

        tv_generos.setText(genresList);
        */
        //FECHA DE ESTRENO
        TextView tv_estreno = (TextView) findViewById(R.id.tv_estreno);
        tv_estreno.setText(movie.getReleaseDate());
        /*
        //IDIOMAS
        TextView tv_idiomas = (TextView) findViewById(R.id.tv_idiomaOrig);

        String idiomasList = "";

        List<SpokenLanguage> idiomas = movie.getSpokenLanguages();
        for (SpokenLanguage idioma: idiomas) {

            idiomasList = idiomasList +", "+idioma.getName();
        }

        tv_idiomas.setText(idiomasList);
        */








    }

}