package com.sdm.sergio.mytrack.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdm.sergio.mytrack.R;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //
        //Ir a la vista de perfil del usuario
        setContentView(R.layout.a_profile);

    }

    //Rellenar datos del usuario
    public void rellenarDatos(){

        //Nombre del Usuario
        TextView nombreUser = (TextView) findViewById(R.id.user_profile_name);

        //Imagen del Usuario
        ImageView imagenUser = (ImageView) findViewById(R.id.user_profile_photo);



        }

    public boolean onCreateOptionsMenu(Menu menu) //Enlazar el menu
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

}

