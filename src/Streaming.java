import util.TextUI;

import java.util.ArrayList;
import util.*;

public class Streaming {

    private TextUI ui;
    private FileIO io;
    private User currentUser;
    private UserHandler userHandler;
    Movies movie = new Movies();


    public Streaming() {

    }


    public void streamSetup() {
        UserHandler userhandler = null;

        try {
            userhandler = new UserHandler("src/user.csv");
            userhandler.loadUsers();
        } catch (Exception e) {
            // skriv til bruger og luk program
            System.out.println("Desværre kan vi ikke køre programmet lige nu");
            System.exit(0);
        }
        // ArrayList<String> users = io.readUserData("src/users");

        if (ui.getUserInput("Already user? Y/N").equalsIgnoreCase("Y")) {
            loginMenu(userhandler);
        } else {
            createUserMenu(userhandler);
        }

    }

    public void mainMenu() {
        String input = ui.getUserInput("Please choose between watching; 1/2 \n" + "1. Movies\n" + "2.Series");
        switch (input){
            case "1":
                movies.movieMenu();
            break;
            case "2":
                series.seriesMenu();
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
