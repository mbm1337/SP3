import Content.*;
import util.*;

import java.util.ArrayList;

public class Streaming {

    private TextUI ui;
    private FileIO io;
    private User currentUser;

    Series series = new Series();
    UserHandler userHandler;
    ArrayList <User> users = new ArrayList<>();
    DBConnector dbConnector = new DBConnector();
    ArrayList<Movies> movies = new ArrayList<>();


    public Streaming() {
        dbConnector.loadUsers();
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

    public void mainMenu(User currentUser) {
        String input = ui.getUserInput("Please choose between; 1/2/3 \n" + "1. Movies\n" + "2. Series\n" + "3. Quit");
        switch (input){
            case "1":

            break;
            case "2":
                series.seriesMenu(currentUser);

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

    public void loginMenu() {

            String username = ui.getUserInput("Please enter your username:");
            String password = ui.getUserInput("Please enter your password:");
            if (userHandler.login(username, password)) {
                ui.displayMessage("Welcome " + username);
                for(User user: users)
                    if(user.getUsername().equalsIgnoreCase(username)) {
                        currentUser = user;
                        mainMenu(currentUser);
                    }
            } else {
                ui.displayMessage("Sorry, the username or password is incorrect");
                streamSetup();
            }
    }

    public void createUserMenu () {
            String userName = ui.getUserInput("Please enter a username:");
            String password = ui.getUserInput("Please enter a password:");

            if (userHandler.createUser(userName, password)) {
                ui.displayMessage("Welcome " + userName);
                for(User user : users) {
                    if(user.getUsername().equalsIgnoreCase(userName)){
                        currentUser = user;
                        mainMenu(currentUser);
                    }
                }

            } else {
                ui.displayMessage("Sorry, the username or password can not be used");
                streamSetup();
            }
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
                currentUser.displayWatchedList();
                break;
            case "5":
                currentUser.displaySavedList();
            default:
                movieMenu(currentUser);
        }
    }

    public void genreSearch() {
        String input = ui.getUserInput("Please type the desired genre:");

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

    public void save() {
        dbConnector.saveUsers();
    }
}
