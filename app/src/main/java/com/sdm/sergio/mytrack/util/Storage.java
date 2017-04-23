package com.sdm.sergio.mytrack.util;

import com.sdm.sergio.mytrack.model.InfoMovie;
import com.sdm.sergio.mytrack.model.TMDBMovie;

import java.util.HashMap;

/**
 * Created by Sergio on 22/04/2017.
 */

public class Storage {
    private static Storage instance;
    private InfoMovie[] discover;
    private HashMap<String,TMDBMovie> fullmovies = new HashMap<String, TMDBMovie>();


    private Storage(){}

    public static Storage getInstance(){
        if(instance==null)
            instance=  new Storage();
         return instance;
    }

    public InfoMovie[] getDiscover(){
        return discover;
    }
    public void setDiscover(InfoMovie[] discover) {
        this.discover = discover;
    }
    public void addFullMovie(String id, TMDBMovie fullmovie){fullmovies.put(id,fullmovie);}
    public TMDBMovie extractFullMovie(String id){return fullmovies.get(id);}

}
