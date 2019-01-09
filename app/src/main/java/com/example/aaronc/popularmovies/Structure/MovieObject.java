package com.example.aaronc.popularmovies.Structure;

import java.io.Serializable;

public class MovieObject implements Serializable {
    public int voteCount;
    public int idNum;
    public boolean isVideo;
    public int voteAve;
    public String movieTitle;
    public double popularty;
    public String posterPath;
    public String language;
    public String originalTitle;
    public int[] genreIDs = new int[5];
    public String backdropPath;
    public boolean adult;
    public String overview;
    public String releaseDate;
}
