package com.sdm.sergio.mytrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 23/03/2017.
 */

public class Movie {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("ids")
    @Expose
    private Ids ids ;

    private TMDBMovie fullmovie;

    public Movie(String tittle, int year) {
        this.title = tittle;
        this.year = year;
    }

    public Ids getIds() {
        return ids;
    }

    public void setFullmovie(TMDBMovie fullmovie){this.fullmovie = fullmovie;}

    public TMDBMovie getFullmovie() {
        return fullmovie;
    }

    public String getTittle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.title = tittle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}