package com.sdm.sergio.mytrack.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 29/03/2017.
 */

public class Ids {
    @SerializedName("trakt")
    @Expose
    private int trakt;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("imdb")
    @Expose
    private String imdb;
    @SerializedName("tmdb")
    @Expose
    private int tmdb;

    public Ids(int trakt, String slug, String imdb, int tmdb) {
        this.trakt = trakt;
        this.slug = slug;
        this.imdb = imdb;
        this.tmdb = tmdb;
    }
    public Integer getTrakt() {
        return trakt;
    }

    public void setTrakt(Integer trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public Integer getTmdb() {
        return tmdb;
    }

    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }

}

