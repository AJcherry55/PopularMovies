package com.example.aaronc.popularmovies.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaronc.popularmovies.Adapter.AndroidMovieGridViewAdapter;
import com.example.aaronc.popularmovies.R;
import com.example.aaronc.popularmovies.utils.JsonUtils;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public AndroidMovieGridViewAdapter androidMovieGridViewAdapter;
    GridView mainMenuGrid;
    ProgressBar loadProgress;
    //PLACE YOUR API_KEY HERE TO ACCESS THE MOVIESDB API CONTENT
    String API_KEY = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainMenuGrid = (GridView) findViewById(R.id.movies_grid);
        mainMenuGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                androidMovieGridViewAdapter.onItemClick(parent, view, position, id);
            }
        });
        FetchJSPfromURL fetch = new FetchJSPfromURL();
        String sortPopular = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
        fetch.execute(sortPopular);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.popular) {
            String sortPopular = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
            FetchJSPfromURL fetch = new FetchJSPfromURL();
            fetch.execute(sortPopular);
            return true;
        } else if (id == R.id.highest_rated) {
            String sortRanked = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US&sort_by=vote_average.desc&include_adult=false&include_video=false&page=1";
            FetchJSPfromURL fetch = new FetchJSPfromURL();
            fetch.execute(sortRanked);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FetchJSPfromURL extends AsyncTask<String, Void, Void> {
        String data = "";
        List movieList;
        String testUrl;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            boolean onlineAccess = false;
            testUrl = strings[0];
            while (!onlineAccess) {
                onlineAccess = isOnline();
            }
            try {
                URL newURL = new URL(testUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) newURL.openConnection();
                InputStream stream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while (line != null) {
                    line = reader.readLine();
                    data = data + line;
                }
                movieList = new JsonUtils().JsonUtils(data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            androidMovieGridViewAdapter = new AndroidMovieGridViewAdapter(getBaseContext(), movieList);
            androidMovieGridViewAdapter.notifyDataSetChanged();
            mainMenuGrid.setAdapter(androidMovieGridViewAdapter);
        }

        // ICMP
        public boolean isOnline() {
            Runtime runtime = Runtime.getRuntime();
            try {
                Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                int exitValue = ipProcess.waitFor();
                return (exitValue == 0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return false;
        }
    }

}
