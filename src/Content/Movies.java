package Content;

import util.*;

import java.util.ArrayList;

public class Movies {
    private String title;
    private String[] genre;
    private int year;
    private float rating;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    User currentUser;


    public Movies() {

        genre = new String[20];
        genre[0] = "Crime";
        genre[1] = "Drama";
        genre[2] = "Biography";
        genre[3] = "Sport";
        genre[4] = "History";
        genre[5] = "Romance";
        genre[6] = "War";
        genre[7] = "Mystery";
        genre[8] = "Adventure";
        genre[9] = "Family";
        genre[10] = "Fantasy";
        genre[11] = "Thriller";
        genre[12] = "Horror";
        genre[13] = "Film-Noir";
        genre[14] = "Action";
        genre[15] = "Sci-fi";
        genre[16] = "Comedy";
        genre[17] = "Musical";
        genre[18] = "Western";
        genre[19] = "Music";
    }

    public void movieMenu(User user) {
        currentUser = user;
        String inputMovies = ui.getUserInput(
                "Please choose between following options;\n" +
                        "1. Search for a specific movie\n" +
                        "2. Search for a genre and receive all movies in this category\n" +
                        "3. Search by decade and receive all movies in a period \n" +
                        "4. Review your list of watched movies and series\n" +
                        "5. Review your list of saved movies and series");
        switch (inputMovies) {
            case "1":
                movieSearch();
                break;
            case "2":
                genreSearch();
                break;
            case "3":
                searchByYear();
                break;
            case "4":
                currentUser.watchedList();
                break;
            case "5":
                currentUser.savedList();
            default:
                movieMenu(currentUser);
        }
    }

    public void movieSearch() {

        String input = ui.getUserInput("Please type the desired movie:");
        String[] moviesData = io.readMoviesData("src/Files/Movies.csv", 100);

        for (String s : moviesData) {
            String[] line = s.split(";");
            this.title = line[0].trim();
            this.year = Integer.parseInt(line[1].trim());
            this.genre = line[2].split("\\.");
            this.rating = Float.parseFloat(line[3].trim());
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

    public void genreSearch() {
        String input = ui.getUserInput("Please type the desired genre:");
        String[] moviesData = io.readMoviesData("src/Files/Movies.csv", 100);

        for (String s : moviesData) {
            String[] line = s.split(";");
            this.title = line[0].trim();
            this.year = Integer.parseInt(line[1].trim());
            this.genre = line[2].split("\\.");
            this.rating = Float.parseFloat(line[3].trim());

            for (String s2 : genre) {
                if (input.equalsIgnoreCase(s2.trim())) {
                    ui.displayMessage(title);
                }
            }
        }
        movieSearch();
    }

    public void searchByYear() {
        ArrayList<String> yearSeach = new ArrayList<>();
        int input = Integer.parseInt(ui.getUserInput("Please type the desired decade:"));
        String[] moviesData = io.readMoviesData("src/Files/Movies.csv", 100);

        for (String s : moviesData) {
            String[] line = s.split(";");
            this.title = line[0].trim();
            this.year = Integer.parseInt(line[1].trim());
            this.genre = line[2].split("\\.");
            this.rating = Float.parseFloat(line[3].trim());

            if(year >= input && year < (input+10)){
                yearSeach.add(title);
            }
        }
        if (yearSeach.size()>0){
            for (String s : yearSeach){
                ui.displayMessage(s);
            }
            movieSearch();
        } else{
            ui.displayMessage("Sorry, we have no movies from this decade");
            searchByYear();
        }
    }
}