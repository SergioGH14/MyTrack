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
import com.sdm.sergio.mytrack.model.GenreMovie;

import java.util.List;

/**
 * Created by Sergio on 24/04/2017.
 */

public class GenreGridAdapter extends BaseAdapter {
    private Context context;
    private List<GenreMovie> list;

    public GenreGridAdapter(List<GenreMovie> list, Context context) {
        this.list = list;
        this.context=context;
    }

    public void setMovieList(List<GenreMovie> list){this.list = list;}

    @Override
    public int getCount() {return list.size();}

    @Override
    public GenreMovie getItem(int position) {
        return list.get(position);
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

        GenreMovie item = getItem(position);

        Glide.with(imagenMovie.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+item.getPosterPath())
                .into(imagenMovie);

        nombreMovie.setText(item.getTitle());

        return view;
    }
}
