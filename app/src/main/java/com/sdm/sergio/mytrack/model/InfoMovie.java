package com.sdm.sergio.mytrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoMovie {
    @SerializedName("watchers")
    @Expose
    private int watchers;
    @SerializedName("movie")
    @Expose
    private Movie movie;

    public InfoMovie(int watchers, Movie movie) {
        this.watchers = watchers;
        this.movie = movie;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public String toString(){
        String res = "Nombre "+movie.getTittle()+" y vista "+watchers+" veces.";
        return res;
    }
}

