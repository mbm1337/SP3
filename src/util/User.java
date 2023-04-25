package Util;

import java.util.ArrayList;

public class User {
    String name;
    String password;

    TextUI ui = new TextUI();

    public ArrayList<String> watchedList = new ArrayList<>();

    public ArrayList<String> savedList = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void watchedList() {
        for (String s : watchedList) {
            ui.displayMessage(s);
        }
    }

    public void savedList() {
        for (String s : savedList) {
            ui.displayMessage(s);
        }
    }
    public ArrayList<String> getWatchedList() {
        return watchedList;
    }

    public ArrayList<String> getSavedList() {
        return savedList;
    }


}