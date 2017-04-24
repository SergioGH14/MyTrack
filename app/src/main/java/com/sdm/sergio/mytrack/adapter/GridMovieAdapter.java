package com.sdm.sergio.mytrack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdm.sergio.mytrack.R;
import com.sdm.sergio.mytrack.model.InfoMovie;
import com.sdm.sergio.mytrack.model.Movie;

/**
 * Created by Sergio on 20/04/2017.
 */

public class GridMovieAdapter extends BaseAdapter {
    private Context context;
    private InfoMovie[] list;

    public GridMovieAdapter(InfoMovie[] list, Context context) {
        this.list = list;
        this.context=context;
    }

    public void setMovieList(InfoMovie[] list){this.list = list;}

    @Override
    public int getCount() {return list.length;}

    @Override
    public Movie getItem(int position) {
        return list[position].getMovie();
    }

    @Override
    public long getItemId(int position) {return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.i_movie, viewGroup, false);
        }

        ImageView imagenMovie = (ImageView) view.findViewById(R.id.movie_image);
        TextView nombreMovie = (TextView) view.findViewById(R.id.movie_name);

        Movie item = getItem(position);

        Glide.with(imagenMovie.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+item.getFullmovie().getPosterPath())
                .into(imagenMovie);

        nombreMovie.setText(item.getTittle());

        return view;
    }


}

