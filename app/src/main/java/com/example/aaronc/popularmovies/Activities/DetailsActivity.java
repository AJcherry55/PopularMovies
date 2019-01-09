package com.example.aaronc.popularmovies.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aaronc.popularmovies.R;
import com.example.aaronc.popularmovies.Structure.MovieObject;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    MovieObject movieObject;
    ImageView poster;
    TextView release;
    TextView rank;
    Button markFavorite;
    TextView overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        toolbar2.getContentInsetEnd();

        Bundle bundleObject = getIntent().getExtras();
        movieObject = (MovieObject) bundleObject.getSerializable("movies");
        poster = findViewById(R.id.details_poster);
        release = findViewById(R.id.release_date);
        rank = findViewById(R.id.ratings);
        markFavorite = findViewById(R.id.favorites_button);
        overview = findViewById(R.id.overview);
        toolbar2.setTitle(movieObject.movieTitle);
        Picasso.with(this).load(movieObject.posterPath).into(poster);
        release.setText(movieObject.releaseDate);
        rank.setText(movieObject.voteAve + "/10");
        overview.setText(movieObject.overview);


    }

}
