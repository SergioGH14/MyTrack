package com.sdm.sergio.mytrack.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentGenresGrid;
import com.sdm.sergio.mytrack.model.GenreMovieContainer;
import com.sdm.sergio.mytrack.util.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Sergio on 24/04/2017.
 */

public class MovieGenresReq extends AsyncTask <Void,Void,Void>{

    private Uri.Builder builder = new Uri.Builder();
    private String res;
    private String id;
    FragmentTransaction transaction;
    private GenreMovieContainer movielist;


    public MovieGenresReq(FragmentTransaction transaction,String id) {
        super();
        this.transaction = transaction;
        this.id=id;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            builder.scheme("https");
            builder.authority("api.themoviedb.org");
            builder.appendPath("3");
            builder.appendPath("genre");
            builder.appendPath(id);
            builder.appendPath("movies");
            builder.appendQueryParameter("api_key", "c4127f942963e453b0d043dc2d4510ea");
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(reader);
                GsonBuilder gbuilder = new GsonBuilder();
                Gson gson = gbuilder.create();
                movielist = gson.fromJson(reader,GenreMovieContainer.class);

                Storage.getInstance().setGenre(movielist);

                connection.disconnect();}
        }catch (JsonParseException e) {
            Log.d("DEBUG", "JSONParseException:" + e.getMessage());
        } catch (MalformedURLException e2) {
            Log.d("DEBUG", "MalformedURLException: " + e2.getMessage());
        } catch (ProtocolException e3) {
            Log.d("DEBUG", "ProtocolException:" + e3.getMessage());
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void params) {
        super.onPostExecute(params);
        /*Esto es de prueba hasta que funcione la peticion
         * Cuando funcione, deberá poner el imovie en Storage y hacer commit a la transaccion del fragment trending
         */
        FragmentGenresGrid context = FragmentGenresGrid.newInstance();
        transaction.replace(R.id.genre_container,context);
        transaction.commit();

    }
}