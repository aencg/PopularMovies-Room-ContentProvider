package com.example.moviesprovidertestapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moviesprovidertestapp.data.Movie;
import com.example.moviesprovidertestapp.data.MovieContract;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    static final String TAG = MainActivity.class.getName();
    static final int TEST_LOADER = 555;

    ListView mListView;
    List<Movie> mMovies = new ArrayList<>();
    ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);

        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.withAppendedPath(MovieContract.CONTENT_URI, mMovies.get(position).getId());

                resolver.delete(uri,null, null);
                LoaderManager.getInstance(MainActivity.this).restartLoader( TEST_LOADER,
                        null, MainActivity.this);
            }
        });

        Bundle queryBundle = new Bundle();
        LoaderManager.getInstance(this).initLoader( TEST_LOADER, queryBundle, this);
    }


    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List<Movie>>(this) {


            List<Movie> mMovieList;

            @Override
            protected void onStartLoading() {
                forceLoad();
            }

            @Override
            public List<Movie> loadInBackground() {


                List<Movie> movies = new ArrayList<Movie>();

                ContentResolver resolver = getContentResolver();

                // Call the query method on the resolver with the correct Uri from the contract class
                Cursor cursor = resolver.query(MovieContract.CONTENT_URI,
                        null, null, null, null);
                if(cursor == null )
                    return null;
                int columnId = cursor.getColumnIndex(MovieContract.COLUMN_ID);
                int columnTitle = cursor.getColumnIndex(MovieContract.COLUMN_TITLE);
                int columnPoster = cursor.getColumnIndex(MovieContract.COLUMN_POSTER);
                int columnAv = cursor.getColumnIndex(MovieContract.COLUMN_VOTE_AVERAGE);
                int columnSynopsis = cursor.getColumnIndex(MovieContract.COLUMN_SYNOPSIS);
                int columnDate = cursor.getColumnIndex(MovieContract.COLUMN_RELEASE_DATE);

                while(cursor.moveToNext()){
                    cursor.toString();
                    Movie movie = new Movie();
                    movie.setId(cursor.getString(columnId));
                    movie.setTitle(cursor.getString(columnTitle));
                    movie.setMoviePoster(cursor.getString(columnPoster));
                    movie.setVoteAverage(cursor.getString(columnAv));
                    movie.setSynopsis(cursor.getString(columnSynopsis));
                    movie.setReleaseDate(cursor.getString(columnDate));
                    movies.add(movie);
                }

                //access content provider
                return movies;
            }
            @Override
            public void deliverResult(List<Movie> results) {
                mMovieList = results;
                super.deliverResult(mMovieList);
            }
        };

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {

        mArrayAdapter.clear();
        String stringSalida;
        if(data!=null && data.size() != 0){
            mMovies = data;
            for(Movie movie: data){
                mArrayAdapter.add(movie.getTitle());
            }
        }   else{
            stringSalida = "Error loading data or data is empty";
            mArrayAdapter.add(stringSalida);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }
}