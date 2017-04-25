package com.sdm.sergio.mytrack.util;

import android.content.Context;
import android.util.Log;

import com.sdm.sergio.mytrack.model.InfoMovie;
import com.sdm.sergio.mytrack.model.TMDBMovie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by Sergio on 22/04/2017.
 */

public class Storage {
    private static Storage instance;
    private InfoMovie[] discover;
    private Context context;
    private HashMap<String,TMDBMovie> fullmovies = new HashMap<String, TMDBMovie>();


    private Storage(Context context){this.context=context;}

    public static void StorageInit(Context context){instance=  new Storage(context);}

    public static Storage getInstance(){
         return instance;
    }

    public InfoMovie[] getDiscover(){
        return discover;
    }
    public void setDiscover(InfoMovie[] discover) {
        this.discover = discover;
    }

    public void addFullMovie(String id, TMDBMovie fullmovie, InputStreamReader reader)throws UnsupportedEncodingException, IOException {
        fullmovies.put(id,fullmovie);

        String json = org.apache.commons.io.IOUtils.toString(reader);

//        BufferedReader br =  new BufferedReader(reader);
//        StringBuilder sb = new StringBuilder();
//        String line;
//
//        try {
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {e.printStackTrace();}
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        String json = sb.toString();

        TMDBMovieSQLHelper.getInstance(context).addTMDBMovie(Integer.parseInt(id),json);

    }

    public TMDBMovie extractFullMovie(String id){
        if(fullmovies.get(id)!=null){
            return fullmovies.get(id);}
        else{

            TMDBMovie aux = TMDBMovieSQLHelper.getInstance(context).getTMDBMovie(Integer.parseInt(id));

            if(aux!=null)
                return aux;
        }
        return null;}

}
