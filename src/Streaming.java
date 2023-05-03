import Content.*;
import util.*;

import java.util.ArrayList;

public class Streaming {

    TextUI ui = new TextUI();
    ArrayList<Series> series;
    ArrayList <User> users;
    ArrayList<Movies> movies;
    DBConnector dbConnector = new DBConnector();

    public Streaming() {
        users = dbConnector.loadUsers();
        movies = dbConnector.loadMovies();
        series = dbConnector.loadSeries();
       dbConnector.loadWatchedSeries();
        dbConnector.loadWatchedMovies();
       dbConnector.loadSavedSeries();
        dbConnector.loadSavedMovies();
        System.out.println("Almost there..");

    }

    public void streamSetup() {
        String s1 = ui.getUserInput("Already user? Y/N");
        if (s1.equalsIgnoreCase("Y")) {
            loginMenu();
        } else if(s1.equalsIgnoreCase("N")){
            createUserMenu();
        } else {
            ui.displayMessage("Not an option, please try again");
            streamSetup();
        }
    }
    public void loginMenu() {

        String username = ui.getUserInput("Please enter your username:");
        String password = ui.getUserInput("Please enter your password:");
        boolean tjek = true;
        for (User user : users){
            if(username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
                ui.displayMessage("Welcome " + user.getUsername());
                tjek = false;
                mainMenu(user);
                break;
            }
        }
        if(tjek) {
            if(ui.getUserInput("enter 1 to try again or 2 to create account").equalsIgnoreCase("1")){
                loginMenu();
            } else {
                createUserMenu();
            }
        }
    }

    public void createUserMenu () {
        String userName = ui.getUserInput("Please enter a username:");
        String password = ui.getUserInput("Please enter a password:");
        boolean tjek = false;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(userName)) {
                ui.displayMessage("This username has already been taken");
                tjek = true;
                break;
            }
        }
        if(tjek){
            createUserMenu();
        } else {
            User user = new User(users.size()+1,userName,password);
            users.add(user);
            loginMenu();
        }
    }

    public void mainMenu(User currentUser) {
        String input = ui.getUserInput("Please choose between; 1/2/3 \n" + "1. Movies\n" + "2. Series\n" + "3. Quit");
        switch (input){
            case "1":
                 movieMenu(currentUser);
            break;
            case "2":
                seriesMenu(currentUser);
            break;
            case "3":
                save();
                ui.displayMessage("Goodbye!");
                break;
            default:
                ui.displayMessage("This is not an option");
                mainMenu(currentUser);
        }
    }


    public void movieMenu(User currentUser) {
        boolean quit = false;
        while (!quit) {
            String inputMovies = ui.getUserInput(
                    "Please choose between following options;\n" +
                            "1. Search for a specific movie\n" +
                            "2. Search for a genre and receive all movies in this category\n" +
                            "3. Search by decade and receive all movies in a period \n" +
                            "4. Review your list of watched movies\n" +
                            "5. Review your list of saved movies\n"+
                            "6. Go back\n" +
                            "7. Save and quit");
            switch (inputMovies) {
                case "1":
                    movieSearch(currentUser);
                    break;
                case "2":
                    genreSearch("movie", currentUser);
                    break;
                case "3":
                    searchByYear("movie",currentUser);
                    break;
                case "4":
                    currentUser.displayWatchedMovies();
                    break;
                case "5":
                    currentUser.displaySavedMovies();
                    break;
                case "6":
                    quit = true;
                    mainMenu(currentUser);
                    break;
                case "7":
                    quit = true;
                    save();
                    break;
                default:
                    movieMenu(currentUser);
            }
        }
    }

    public void genreSearch(String kind, User currentUser) {
       if(kind.equalsIgnoreCase("movie")){
           String input = ui.getUserInput("Please type the desired genre:");
           for (Movies s : movies) {
               for (String s2 : s.getGenre()) {
                   if (input.equalsIgnoreCase(s2)) {
                       ui.displayMessage(s.getTitle());
                   }
               }
           }
           movieSearch(currentUser);
       } else if (kind.equalsIgnoreCase("series")) {
           String input = ui.getUserInput("Please type the desired genre:");
           for (Series s : series) {
               for (String s2 : s.getGenre()) {
                   if (input.equalsIgnoreCase(s2.trim())) {
                       ui.displayMessage(s.getTitle());
                   }
               }
           }
           movieSearch(currentUser);
       } else {
           ui.displayMessage("something went wrong. genre");
       }
    }

    public void searchByYear(String kind, User user) {
        if (kind.equalsIgnoreCase("movie")) {
            int input = Integer.parseInt(ui.getUserInput("Please type the desired decade:"));
            for (Movies s : movies) {
                if (s.getYear() >= input && s.getYear() < (input + 10)) {
                    ui.displayMessage(s.getTitle());
                } else {
                    ui.displayMessage("Sorry, we have no movies from this decade");
                    searchByYear("movie", user);
                }
            }
            movieSearch(user);
        } else if(kind.equalsIgnoreCase("series")){
            boolean tjek = false;
            int input = Integer.parseInt(ui.getUserInput("Please type the year it was running:"));
            for(Series s : series){
                for(String syear : s.getGenre()){
                    if(input == Integer.parseInt(syear)) {
                        tjek = true;
                        ui.displayMessage(s.getTitle());
                    }
                }
            }
            if(tjek){
                seriesSearch(user);
            }
        } else {
            ui.displayMessage("something went wrong. year");
        }
    }

    public void movieSearch(User currentUser) {

        String input = ui.getUserInput("Please type the desired movie:");

        for (Movies movies : movies) {
            if (movies.getTitle().equalsIgnoreCase(input)) {
                String input2 = ui.getUserInput("Choose between: 1/2\n" +
                        "1. Watch the chosen movie\n" +
                        "2. Save movie to your saved list");
                if (input2.equalsIgnoreCase("1")) {
                    ui.displayMessage("You are now watching " + movies.getTitle());
                    currentUser.addToWatchedMovies(movies);
                    ui.displayMessage("\nThank you for watching. Do you want to watch more?");

                } else if (input2.equalsIgnoreCase("2")) {
                    currentUser.addToSavedMovies(movies);
                    ui.displayMessage("The movie was saved on your saved list.");

                } else {
                    ui.displayMessage("This is not an option");
                    movieSearch(currentUser);
                }
            }
        }
    }

    public void seriesMenu(User currentUser){
        boolean quit = false;
        while(!quit) {
            String inputSeries = ui.getUserInput("Please choose between following options;\n" +
                    "1. Search for a specific series\n" +
                    "2. Search for a genre and receive all series in this category\n" +
                    "3. Search a year it was running\n"+
                    "4. Review your list of watched movies and series\n" +
                    "5. Review your list of saved movies and series\n" +
                    "6. Go back\n" +
                    "7. Save and quit");
            switch (inputSeries) {
                case "1":
                    seriesSearch(currentUser);
                    break;
                case "2":
                    genreSearch("series",currentUser);
                    break;
                case "3":
                    searchByYear("series",currentUser);
                case "4":
                    currentUser.displayWatchedSeries();
                    break;
                case "5":
                    currentUser.displaySavedSeries();
                    break;
                case "6":
                    quit = true;
                    mainMenu(currentUser);
                    break;
                case "7":
                    quit = true;
                    save();
                    break;
                default:
                    ui.displayMessage("This is not an option");
                    seriesMenu(currentUser);
            }
        }
    }

    public void seriesSearch(User user){
        String input = ui.getUserInput("Please type the desired series:");
        for (Series s : series) {
            if (input.equalsIgnoreCase(s.getTitle())) {
                String s2 = ui.getUserInput("Choose between: 1/2\n" +
                        "1. Watch the chosen series\n" +
                        "2. Save series to your saved list");
                if (s2.equalsIgnoreCase("1")) {
                    ui.displayMessage("You are now watching " + s.getTitle());
                    user.addToWatchedSeries(s);
                    ui.displayMessage("\nThank you for watching. Do you want to watch more?");

                } else if(s2.equalsIgnoreCase("2")) {
                    user.addToSavedSeries(s);
                    System.out.println("The series was saved on your watch list");
                }else {
                    ui.displayMessage("This is not an option");
                    seriesSearch(user);
                }
            }
        }
    }

    public void save() {
        dbConnector.save(users);
        dbConnector.saveLists(users);
        ;
    }
}
