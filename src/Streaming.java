import Content.Movies;
import Content.Series;
import Util.FileIO;
import Util.TextUI;
import Util.User;

import Util.*;

import java.util.ArrayList;


public class Streaming {

    private TextUI ui;
    private FileIO io;
    private User currentUser;
    Movies movies = new Movies();
    Series series = new Series();
    UserHandler userHandler;

    ArrayList <User> users = new ArrayList<>();


    public Streaming() {

    }

    public void streamSetup() {
        ui = new TextUI();
        io = new FileIO();
        try {
            userHandler = new UserHandler("src/Files/user.csv");
            users = userHandler.loadUsers();
        } catch (Exception e) {
            System.out.println("The program cannot run at the moment");
            System.exit(0);
        }

        if (ui.getUserInput("Already user? Y/N").equalsIgnoreCase("Y")) {
            userHandler.saveUsers();
            loginMenu(userHandler);

        } else {
            createUserMenu(userHandler);
            userHandler.saveUsers();

        }
    }

    public void mainMenu() {
        String input = ui.getUserInput("Please choose between; 1/2/3 \n" + "1. Movies\n" + "2. Series\n" + "3. Quit");
        switch (input){
            case "1":
                movies.movieMenu(currentUser);
                mainMenu();
            break;
            case "2":
                series.seriesMenu(currentUser);
                mainMenu();
            break;
            case "3":
                io.saveSavedList("src/Files/savedList.csv",currentUser,movies,series);
                io.saveWatchedList("src/Files/watchedList.csv",currentUser,movies,series);
                ui.displayMessage("Goodbye!");
                break;
            default:
                ui.displayMessage("This is not an option");
                mainMenu();
        }
    }

    public void loginMenu(UserHandler userHandler) {

            String userName = ui.getUserInput("Please enter your username:");
            String password = ui.getUserInput("Please enter your password:");
            if (userHandler.login(userName, password)) {
                ui.displayMessage("Welcome " + userName);
            } else {
                ui.displayMessage("Sorry, the username or password is incorrect");
                streamSetup();
            }
        currentUser = new User(userName,password);
    }

    public void createUserMenu (UserHandler userHandler) {
            String userName = ui.getUserInput("Please enter a username:");
            String password = ui.getUserInput("Please enter a password:");
            if (userHandler.createUser(userName, password)) {
                ui.displayMessage("Welcome " + userName);
            } else {
                ui.displayMessage("Sorry, the username or password can not be used");
            }
        currentUser = new User(userName,password);
    }





}
