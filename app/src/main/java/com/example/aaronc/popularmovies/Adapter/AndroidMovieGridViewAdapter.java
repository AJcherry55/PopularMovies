package com.example.aaronc.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaronc.popularmovies.Activities.DetailsActivity;
import com.example.aaronc.popularmovies.Activities.MainActivity;
import com.example.aaronc.popularmovies.R;
import com.example.aaronc.popularmovies.Structure.MovieObject;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class AndroidMovieGridViewAdapter extends ArrayAdapter<MovieObject> implements AdapterView.OnItemClickListener {
    private List<MovieObject> mMovieObjectList;


    public AndroidMovieGridViewAdapter(Context context, List<MovieObject> movieObjectList) {
        super(context, 0, movieObjectList);
        mMovieObjectList = movieObjectList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject movieObject = getItem(position);
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_list_item, parent, false);
        }
        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);
        //TextView movieTitle = (TextView)convertView.findViewById(R.id.movie_title);
        //Picasso.get().load(movieObject.posterPath).into(moviePoster);
        Picasso.with(getContext()).load(movieObject.posterPath).into(moviePoster);
        //movieTitle.setText(movieObject.movieTitle);
        return convertView;
    }

    @Override
    public MovieObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("movies", (Serializable) mMovieObjectList);
        intent.putExtras(bundle);
        startActivity(getContext(), intent, bundle);
    }
}
