package util;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String username;
    String password;
    TextUI ui = new TextUI();

    public List<WatchedMovies> watchedMovies;

    public ArrayList<WatchedSeries> watchedSeries;


    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.watchedSeries = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
    }

    public String getUsername() {return username;}
    public String getPassword() {
        return password;
    }

    public void displayWatchedList() {
        for (String s : ) {
            ui.displayMessage(s);
        }
    }

    public void displaySavedList() {
        for (String s : ) {
            ui.displayMessage(s);
        }
    }

    public ArrayList<String> getWatchedList() {
        return ;
    }

    public ArrayList<String> getSavedList() {
        return ;
    }

    public void setWatchedList(ArrayList<String> watchedList) {
         = watchedList;
    }

    public void setSavedList(ArrayList<String> savedList) {
         = savedList;
    }
}