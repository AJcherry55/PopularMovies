package com.example.aaronc.popularmovies.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.aaronc.popularmovies.R;
import com.example.aaronc.popularmovies.Structure.MovieObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<MovieObject> movieObjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundleObject = getIntent().getExtras();
        movieObjectArrayList = (ArrayList<MovieObject>) bundleObject.getSerializable("movies");


    }

}
