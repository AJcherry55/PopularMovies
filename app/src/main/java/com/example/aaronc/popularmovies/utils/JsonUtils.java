package com.example.aaronc.popularmovies.utils;

import android.net.Uri;

import com.example.aaronc.popularmovies.Structure.MovieObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    static JSONObject jsonData;
    private int pageCount;
    int page;
    int totalResults;
    static JSONArray results;
    MovieObject movieObject;
    List<MovieObject> movieObjectList;
    String baseUrl = "http://image.tmdb.org/t/p/w342";
    public List JsonUtils(String jsonMovieList) throws JSONException {
        jsonData = new JSONObject(jsonMovieList);
        page = jsonData.getInt("page");
        totalResults = jsonData.getInt("total_results");
        pageCount = jsonData.getInt("total_pages");
        results = jsonData.getJSONArray("results");
        movieObjectList = new ArrayList<MovieObject>();
        for(int i=0;i<results.length();i++){
            movieObject = new MovieObject();
            movieObject.voteCount     = results.getJSONObject(i).getInt("vote_count");
            movieObject.idNum         = results.getJSONObject(i).getInt("id");
            movieObject.isVideo       = results.getJSONObject(i).getBoolean("video");
            movieObject.voteAve       = results.getJSONObject(i).getInt("vote_average");
            movieObject.movieTitle    = results.getJSONObject(i).getString("title");
            movieObject.popularty     = results.getJSONObject(i).getDouble("popularity");
            movieObject.posterPath    = baseUrl + results.getJSONObject(i).getString("poster_path");
            movieObject.language      = results.getJSONObject(i).getString("original_language");
            movieObject.originalTitle = results.getJSONObject(i).getString("original_title");
            JSONArray ids = results.getJSONObject(i).getJSONArray("genre_ids");
            for(int j = 0;j<ids.length();j++) {
                movieObject.genreIDs[j] = ids.getInt(j);
            }
            movieObject.backdropPath  = results.getJSONObject(i).getString("backdrop_path");
            movieObject.adult         = results.getJSONObject(i).getBoolean("adult");
            movieObject.overview      = results.getJSONObject(i).getString("overview");
            movieObject.releaseDate   = results.getJSONObject(i).getString("release_date");
            movieObjectList.add(movieObject);
        }
        return movieObjectList;
    }
}
