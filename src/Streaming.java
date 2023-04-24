import util.TextUI;

import java.util.ArrayList;
import util.*;

public class Streaming {

    private static TextUI ui;
    private FileIO io;
    private User currentUser;
    private UserHandler userHandler;
    Movies movie = new Movies();


    public Streaming() {

    }


    public void streamSetup() {
        UserHandler userhandler = null;

        try {
            userhandler = new UserHandler("user.csv");
            userhandler.loadUsers();
        } catch (Exception e) {
            // skriv til bruger og luk program
            System.out.println("Desværre kan vi ikke køre programmet lige nu");
            System.exit(0);
        }
        // ArrayList<String> users = io.readUserData("src/users");

        if (ui.getUserInput("Already user? Y/N").equalsIgnoreCase("Y")) {
            loginMenu();
        } else {
            createUserMenu();
        }

    }

    public void mainMenu() {
        if (ui.getUserInput("Please choose between watching; 1/2 \n" + "1. Movies\n" + "2.Series").equalsIgnoreCase("1")) {
            String input = ui.getUserInput("Please choose between following options;\n" + "1. Search for a specific movie\n" + "2. Search for a genre and recieve all movies in this category\n" + "3. Review your list of watched movies\n" + "4. Review your list of saved movies");
            switch (input) {
                case "1":
                    movie.movieSearch();
                    break;
                case "2":
                    movie.genreSearch();
                    break;
                case "3":
                    movie.watchedList();
                    break;
                case "4":
                    movie.savedList();
                    break;
                default:
            }
        } else {
            String input = ui.getUserInput("Please choose between following options;\n" + "1. Search for a specific series\n" + "2. Search for a genre and receive all series in this category\n" + "3. Review your list of watched series\n" + "4. Review your list of saved series");
            switch (input) {
                case "1":
                    Series.seriesSearch();
                    break;
                case "2":
                    Series.genreSearch();
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

    public void loginMenu() {
        String userName = ui.getUserInput("Please enter your username:");
        String password = ui.getUserInput("Please enter your password:");
        if (userHandler.login(userName, password)) {
            ui.displayMessage("Welcome " + userName);
        } else {
            ui.displayMessage("Sorry, the username or password is incorrect");
        }
    }

    public void createUserMenu() {
        String userName = ui.getUserInput("Please enter your username:");
        String password = ui.getUserInput("Please enter your password:");
        if (userHandler.createUser(userName, password)) {
            ui.displayMessage("Welcome " + userName);
        } else {
            ui.displayMessage("Sorry, the username or password can not be used");
        }


    }
}
