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
import com.sdm.sergio.mytrack.model.Genre;
import com.sdm.sergio.mytrack.model.ProductionCountry;
import com.sdm.sergio.mytrack.model.SpokenLanguage;
import com.sdm.sergio.mytrack.model.TMDBMovie;
import com.sdm.sergio.mytrack.util.Storage;

import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private TMDBMovie movie;

    // Floating Buttons
    private FloatingActionMenu menuAdd;
    private com.github.clans.fab.FloatingActionButton fabtrailer;
    private com.github.clans.fab.FloatingActionButton fab1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_fullmovie);

        //Quitar Action Bar
        getSupportActionBar().hide();

        // Cogemos la pelicula mediante la clase Storage gracias al id del Intent
        String id = getIntent().getStringExtra("id");
        movie = Storage.getInstance().extractFullMovie(id);
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

        //DURACION
        TextView tv_duracion = (TextView) findViewById(R.id.tv_duracion);
        tv_duracion.setText(""+movie.getRuntime()+ getString(R.string.min));

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

        //PAÍSES
        TextView tv_paises = (TextView) findViewById(R.id.tv_paises);
        String paisesList = "";

        List<ProductionCountry> paises = movie.getProductionCountries();
        for (ProductionCountry pais: paises) {

            if(paisesList.equals("")){
                paisesList = paisesList + pais.getName();
            }else {
                paisesList = paisesList + ", " + pais.getName();
            }
        }
        tv_paises.setText(paisesList);

        //GENEROS
        TextView tv_generos = (TextView) findViewById(R.id.tv_genero);

        String genresList = "";

        List<Genre> genres = movie.getGenres();
        for (Genre genre: genres) {

            if(genresList.equals("")){
                genresList = genresList + genre.getName();
            }else {
                genresList = genresList +", "+genre.getName();
            }
        }
        tv_generos.setText(genresList);

        //FECHA DE ESTRENO
        TextView tv_estreno = (TextView) findViewById(R.id.tv_estreno);
        tv_estreno.setText(movie.getReleaseDate());

        //IDIOMAS
        TextView tv_idiomas = (TextView) findViewById(R.id.tv_idiomaOrig);

        String idiomasList = "";

        List<SpokenLanguage> idiomas = movie.getSpokenLanguages();
        for (SpokenLanguage idioma: idiomas) {

            if (idiomasList.equals("")) {
                idiomasList = idiomasList + idioma.getName();
            } else {
                idiomasList = idiomasList + ", " + idioma.getName();

            }
        }
        tv_idiomas.setText(idiomasList);

        //PRESUPUESTO
        TextView tv_presupuesto = (TextView) findViewById(R.id.tv_presupuesto);
        tv_presupuesto.setText(""+movie.getBudget()+""+getString(R.string.dolar));

        //INGRESOS
        TextView tv_ingresos = (TextView) findViewById(R.id.tv_ingresos);
        tv_ingresos.setText(""+movie.getRevenue()+""+getString(R.string.dolar));

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
                    if(!movie.getVideos().getResults().isEmpty()){

                        //Cojo url del video
                        String videoUrl = "https://www.youtube.com/watch?v="+movie.getVideos().getResults().get(0).getKey();

                        //Lanzar el intent para que se abra en youtube o internet
                        String action = Intent.ACTION_VIEW;
                        Uri uri = Uri.parse(videoUrl);
                        Intent videoIntent = new Intent(action, uri);
                        startActivity(videoIntent);
                        
                    }
                    else {
                        Toast.makeText(MovieActivity.this, getString(R.string.no_trailer), Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };

}