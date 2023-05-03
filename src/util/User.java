package util;

import Content.Movies;
import Content.Series;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String username;
    String password;
    TextUI ui = new TextUI();

    public List<Movies> watchedMovies;
    public List<Series> watchedSeries;
    public List<Movies> savedMovies;
    public List<Series> savedSeries;


    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.watchedSeries = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.savedMovies = new ArrayList<>();
        this.savedSeries = new ArrayList<>();
    }

    public String getUsername() {return username;}
    public String getPassword() {
        return password;
    }

    public void displayWatchedMovies() {
        for (Movies s : watchedMovies ) {
            ui.displayMessage(s.getTitle());
        }
    }

    public void displayWatchedSeries() {
        for (Series s : watchedSeries ) {
            ui.displayMessage(s.getTitle());
        }
    }

    public void displaySavedMovies() {
        for (Movies s : savedMovies) {
            ui.displayMessage(s.getTitle());
        }
    }

    public void displaySavedSeries() {
        for (Series s : savedSeries) {
            ui.displayMessage(s.getTitle());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWatchedMovies(List<Movies> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<Movies> getSavedMovies() {
        return savedMovies;
    }

    public void setSavedMovies(List<Movies> savedMovies) {
        this.savedMovies = savedMovies;
    }

    public List<Series> getWatchedSeries() {
        return watchedSeries;
    }

    public void setWatchedSeries(List<Series> watchedSeries) {
        this.watchedSeries = watchedSeries;
    }

    public void setSavedSeries(List<Series> savedSeries) {this.savedSeries = savedSeries;}
    public List<Movies> getWatchedMovies() {
        return watchedMovies ;
    }
    public List<Series> getSavedSeries() {
        return watchedSeries;
    }
    public void addToWatchedMovies(Movies movies) {watchedMovies.add(movies);}
    public void addToWatchedSeries(Series series) {watchedSeries.add(series);}
    public void addToSavedSeries(Series tmp) {savedSeries.add(tmp);}
    public void addToSavedMovies(Movies tmp) {savedMovies.add(tmp);}
}