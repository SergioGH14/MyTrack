package com.sdm.sergio.mytrack.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreMovieContainer {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<GenreMovie> results = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_GenreMovies")
    @Expose
    private int totalGenreMovies;

    /**
     * No args constructor for use in serialization
     *
     */
    public GenreMovieContainer() {
    }

    /**
     *
     * @param id
     * @param results
     * @param totalGenreMovies
     * @param page
     * @param totalPages
     */
    public GenreMovieContainer(int id, int page, List<GenreMovie> results, int totalPages, int totalGenreMovies) {
        super();
        this.id = id;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalGenreMovies = totalGenreMovies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<GenreMovie> getGenreMovies() {
        return results;
    }

    public void setGenreMovies(List<GenreMovie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalGenreMovies() {
        return totalGenreMovies;
    }

    public void setTotalGenreMovies(int totalGenreMovies) {
        this.totalGenreMovies = totalGenreMovies;
    }

}