package com.sdm.sergio.mytrack.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdm.sergio.mytrack.R;

public class GenreListAdapter extends ArrayAdapter {

    String[] androidListViewStrings;
    Integer[] imagesId;
    Context context;

    public GenreListAdapter(Activity context, Integer[] imagesId, String[] textListView) {
        super(context, R.layout.f_genres, textListView);
        this.androidListViewStrings = textListView;
        this.imagesId = imagesId;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.i_genres, null, true);
        TextView mtextView = (TextView) viewRow.findViewById(R.id.text_view);
        ImageView mimageView = (ImageView) viewRow.findViewById(R.id.image_view);
        mtextView.setText(androidListViewStrings[i]);
        mimageView.setImageResource(imagesId[i]);
        return viewRow;
    }
}
