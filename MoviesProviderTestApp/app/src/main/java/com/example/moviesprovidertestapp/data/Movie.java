package com.example.moviesprovidertestapp.data;

import android.content.ContentValues;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "movies")
public class Movie implements Serializable {

    public static final String MOVIE_ID = "id";
    public static final String MOVIE_TITLE = "title" ;
    public static final String MOVIE_RELEASE_DATE = "releaseDate";
    public static final String MOVIE_POSTER = "moviePoster";
    public static final String MOVIE_VOTE_AVERAGE = "voteAverage";
    public static final String MOVIE_SYNOPSIS = "synopsis";


    @PrimaryKey @NonNull
    private String id;
    private String title;
    private String releaseDate;
    private String moviePoster;
    private String voteAverage;
    private String synopsis;


    public Movie(@NonNull String id, String title, String releaseDate, String moviePoster, String voteAverage, String synopsis ) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
    }

    @Ignore
    public Movie(){

    }
    @NonNull
    public String getId() { return id;  }
    public void setId(@NonNull String id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", moviePoster='" + moviePoster + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }



    public static Movie fromContentValues
            (ContentValues contentValues) {
        Movie movie = new Movie();
        if (contentValues.containsKey(MOVIE_ID)) {
            movie.setId(contentValues.getAsString(MOVIE_ID));
        } if (contentValues.containsKey(MOVIE_TITLE)) {
            movie.setTitle(contentValues.getAsString(MOVIE_TITLE));
        } if (contentValues.containsKey(MOVIE_RELEASE_DATE)) {
            movie.setReleaseDate(
                    contentValues.getAsString(MOVIE_RELEASE_DATE));
        } if (contentValues.containsKey(MOVIE_POSTER)) {
            movie.setMoviePoster
                    (contentValues.getAsString(MOVIE_POSTER));
        }if (contentValues.containsKey(MOVIE_VOTE_AVERAGE)) {
            movie.setVoteAverage
                    (contentValues.getAsString(MOVIE_VOTE_AVERAGE));
        }
        if (contentValues.containsKey(MOVIE_SYNOPSIS)) {
            movie.setSynopsis
                    (contentValues.getAsString(MOVIE_SYNOPSIS));
        }
        return movie;
    }

}
