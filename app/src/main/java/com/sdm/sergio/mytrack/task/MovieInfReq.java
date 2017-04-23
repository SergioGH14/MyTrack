package com.sdm.sergio.mytrack.task;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.sdm.sergio.mytrack.model.TMDBMovie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MovieInfReq extends AsyncTask<Void,Void,Void> {

    private Uri.Builder builder = new Uri.Builder();
    private String id;
    private TMDBMovie movie;

    public MovieInfReq(String id){
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... params) {
        builder.scheme("https");
        builder.authority("api.themoviedb.org");
        builder.appendPath("3");
        builder.appendPath("movie");
        builder.appendPath(id);
        builder.appendQueryParameter("api_key","c4127f942963e453b0d043dc2d4510ea");
        try {
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            Log.e("HttpToSend",""+connection.toString());
            Log.e("HttpResponse",""+connection.getResponseCode());
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(reader);
                GsonBuilder gbuilder = new GsonBuilder();
                Gson gson = gbuilder.create();
                movie = gson.fromJson(reader,TMDBMovie.class);


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


    }
}