package com.sdm.sergio.mytrack.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.fragment.FragmentNotFound;
import com.sdm.sergio.mytrack.fragment.FragmentSearchGrid;
import com.sdm.sergio.mytrack.model.InfoMovie;
import com.sdm.sergio.mytrack.model.TMDBMovie;
import com.sdm.sergio.mytrack.util.Storage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MovieSearchReq extends AsyncTask<Void,Void,Void> {

    private Uri.Builder builder = new Uri.Builder();
    private String res;
    FragmentTransaction transaction;
    private InfoMovie[] imovie;
    String query;

    public MovieSearchReq(FragmentTransaction transaction,String query) {
        super();
        this.transaction = transaction;
        this.query = query;
    }@Override
    protected Void doInBackground(Void... params) {
        try {
            //Construimos la URL de peticion a Trakt.tv
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https");
            builder.authority("api.trakt.tv");
            builder.appendPath("search");
            builder.appendPath("movie");
            builder.appendQueryParameter("query", query);
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("trakt-api-version","2");
            connection.setRequestProperty("trakt-api-key","7fec15e4e6cfa7baa421d55c67242a5617078149da96c4126d219d1e260d6ac5");
            connection.setRequestProperty("X-Pagination-Limit:", "12");
            //Aun no tenemos el token
            //connection.setRequestProperty("Authorization","USER_TOKEN");
            //Comprobamos que la conexion sea correcta
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                //recibimos los datos de la conexion
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                //Creamos el Gson builder
                GsonBuilder gbuilder = new GsonBuilder();
                Gson gson = gbuilder.create();
                //Utilizamos el Gson builder para transformar los datos del inputstream a los de la calse InforMovie.class
                imovie= gson.fromJson(reader,InfoMovie[].class);

                //**************************Informacion ampliada********************************************

                for (int i = 0; i < imovie.length; i++) {
                    TMDBMovie fullmovie = Storage.getInstance().extractFullMovie(imovie[i].getMovie().getIds().getTmdb().toString());
                    if(fullmovie!=null){
                        imovie[i].getMovie().setFullmovie(fullmovie);
                    }
                    else{

                        Uri.Builder builder2 = new Uri.Builder();
                        builder2.scheme("https");
                        builder2.authority("api.themoviedb.org");
                        builder2.appendPath("3");
                        builder2.appendPath("movie");
                        builder2.appendPath(imovie[i].getMovie().getIds().getTmdb().toString());
                        builder2.appendQueryParameter("api_key", "c4127f942963e453b0d043dc2d4510ea");
                        builder2.appendQueryParameter("language", "es-ES");
                        builder2.appendQueryParameter("append_to_response", "videos");
                        try {
                            URL url2 = new URL(builder2.build().toString());
                            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                            connection2.setRequestMethod("GET");
                            connection2.setDoInput(true);
                            Log.e("HttpToSend", "" + connection2.toString());
                            Log.e("HttpResponse", "" + connection2.getResponseCode());
                            if (connection2.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                InputStreamReader reader2 = new InputStreamReader(connection2.getInputStream());
                                InputStreamReader persistence =reader2;
                                GsonBuilder gbuilder2 = new GsonBuilder();
                                Gson gson2 = gbuilder.create();
                                fullmovie = gson2.fromJson(reader2, TMDBMovie.class);
                                imovie[i].getMovie().setFullmovie(fullmovie);
                                Storage.getInstance().addFullMovie(""+fullmovie.getId(),fullmovie,persistence);
                                connection.disconnect();
                            }
                        } catch (JsonParseException e) {
                            Log.d("DEBUG", "JSONParseException:" + e.getMessage());
                        } catch (MalformedURLException e2) {
                            Log.d("DEBUG", "MalformedURLException: " + e2.getMessage());
                        } catch (ProtocolException e3) {
                            Log.d("DEBUG", "ProtocolException:" + e3.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }}
                Storage.getInstance().setSearch(imovie);

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
        if(imovie.length ==0) {
            FragmentNotFound context = FragmentNotFound.newInstance();
            transaction.replace(R.id.genre_container,context);
            transaction.commit();}
        else{
        FragmentSearchGrid context = FragmentSearchGrid.newInstance();
            transaction.replace(R.id.genre_container,context);
            transaction.commit();}



    }
}