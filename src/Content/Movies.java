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

    public void movieSearch() {

        String input = ui.getUserInput("Please type the desired movie:");

        for (String s : ) {
            String[] line = s.split(";");
            this.Movies_ID = Integer.parseInt(line[0]);
            this.title = line[1].trim();
            this.year = Integer.parseInt(line[2].trim());
            this.genre = line[3].split("\\.");
            this.rating = Float.parseFloat(line[4].trim());
            if (input.equalsIgnoreCase(title)) {
                String input2 = ui.getUserInput("Choose between: 1/2\n" +
                        "1. Watch the chosen movie\n" +
                        "2. Save movie to your saved list");
                if (input2.equalsIgnoreCase("1")) {
                    ui.displayMessage("You are now watching " + title);
                    currentUser.watchedList.add(title);
                    ui.displayMessage("\nThank you for watching. Do you want to watch more?");

                } else if (input2.equalsIgnoreCase("2")) {
                    currentUser.savedList.add(title);
                    ui.displayMessage("The movie was saved on your saved list.");

                } else {
                    ui.displayMessage("This is not an option");
                    movieSearch();
                }
            }
        }
    }


}