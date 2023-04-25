import Util.FileIO;
import Util.TextUI;

import java.util.Arrays;

public class Series{
    private String title;
    private String[] genre;
    private String[] year;
    private int minYear;
    private int maxYear;
    private float rating;
    private int minRating;
    private int seasons;
    private int episodes;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Series(){

        genre = new String[20];

        genre[0] = "Talk-Show";
        genre[1] = "Documentary";
        genre[2] = "Crime";
        genre[3] = "Action";
        genre[4] = "Adventure";
        genre[5] = "Drama";
        genre[6] = "Comedy";
        genre[7] = "Fantasy";
        genre[8] = "Animation";
        genre[9] = "Horror";
        genre[10] = "Sci-fi";
        genre[11] = "War";
        genre[12] = "Thriller";
        genre[13] = "Mystery";
        genre[14] = "Biography";
        genre[15] = "History";
        genre[16] = "Family";
        genre[17] = "Western";
        genre[18] = "Romance";
        genre[19] = "Sport";

    }

    public void seriesMenu(){
        String inputSeries = ui.getUserInput("Please choose between following options;\n" + "1. Search for a specific series\n" + "2. Search for a genre and receive all series in this category\n" + "3. Review your list of watched series\n" + "4. Review your list of saved series");
        switch (inputSeries) {
            case "1":
                seriesSearch();
                break;
            case "2":
                genreSearch();
                break;
            case "3":
                watchedList();
                break;
            case "4":
                savedList();
                break;
            default:
        }
    }

    public void seriesSearch(){
        String input = ui.getUserInput("Please type the desired series:");
        String[] seriesData = io.readMoviesData("src/Series.csv", 100);

        for (String s : seriesData) {
            String[] line = s.split(";");
            this.title = line[0].trim();
            this.year = line[1].split("-");
            this.genre = line[2].split("\\.");
            this.rating = Float.parseFloat(line[3].trim());
            if (input.equalsIgnoreCase(title)) {
                if (ui.getUserInput("Choose between: 1/2\n" + "1. Watch the chosen series\n" + "2. Save series to your saved list").equalsIgnoreCase("1")) {
                    ui.displayMessage("You are now watching " + title);
                    //watchedList.add(input);
                } else {
                    //savedList.add(input);
                    System.out.println("The series was saved on your watch list");
                }
            }
        }
    }
    public void genreSearch(){
        String input = ui.getUserInput("Please type the desired genre:");
        String[] seriesData = io.readMoviesData("src/Series.csv", 100);

        for (String s : seriesData) {
            String[] line = s.split(";");
            this.title = line[0].trim();
            this.year = line[1].split("-");
            this.genre = line[2].split("\\.");
            this.rating = Float.parseFloat(line[3].trim());

            for (String s2 : genre) {
                if (input.equalsIgnoreCase(s2.trim())) {
                    ui.displayMessage(title);
                }
            }
        }
        seriesSearch();
    }
    public void watchedList(){

    }
    public void savedList(){

    }



}
