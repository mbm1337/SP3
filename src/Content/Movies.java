package Content;

import util.*;

import java.util.ArrayList;
import java.util.List;

public class Movies {
    private int Movies_ID;
    private String title;
    private List<String> genre;
    private int year;
    private float rating;
    TextUI ui = new TextUI();


    public Movies(int movieID, String title, List<String> genre, int year, float rating) {
        this.Movies_ID = movieID;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }




    public int getMovies_ID() {
        return Movies_ID;
    }

    public void setMovies_ID(int movies_ID) {
        Movies_ID = movies_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}