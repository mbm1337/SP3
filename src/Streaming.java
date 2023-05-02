import Content.*;
import util.*;

import java.util.ArrayList;

public class Streaming {

    private TextUI ui;
    private FileIO io;
    private User currentUser;
    Movies movies;
    Series series;
    UserHandler userHandler;
    ArrayList <User> users;


    public Streaming() {
        this.series = new Series();
        this.movies = new Movies();
        this.users = new ArrayList<>();

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

        String s1 = ui.getUserInput("Already user? Y/N");
        if (s1.equalsIgnoreCase("Y")) {
            userHandler.saveUsers();
            loginMenu(userHandler);

        } else if(s1.equalsIgnoreCase("N")){
            createUserMenu(userHandler);
            userHandler.saveUsers();
        } else {
            ui.displayMessage("Not an option, please try again");
            userHandler.saveUsers();
            streamSetup();
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
                io.saveSavedList("src/Files/savedList.csv",currentUser);
                io.saveWatchedList("src/Files/watchedList.csv",currentUser);
                ui.displayMessage("Goodbye!");
                break;
            default:
                ui.displayMessage("This is not an option");
                mainMenu();
        }
    }

    public void loginMenu(UserHandler userHandler) {

            String username = ui.getUserInput("Please enter your username:");
            String password = ui.getUserInput("Please enter your password:");
            if (userHandler.login(username, password)) {
                ui.displayMessage("Welcome " + username);
                currentUser = new User(username,password);
                currentUser.savedList = io.loadList("src/Files/savedList.csv",currentUser);
                currentUser.watchedList = io.loadList("src/Files/watchedList.csv",currentUser);
                mainMenu();

            } else {
                ui.displayMessage("Sorry, the username or password is incorrect");
                streamSetup();
            }
    }

    public void createUserMenu (UserHandler userHandler) {
            String userName = ui.getUserInput("Please enter a username:");
            String password = ui.getUserInput("Please enter a password:");
            if (userHandler.createUser(userName, password)) {
                ui.displayMessage("Welcome " + userName);
                currentUser = new User(userName,password);
                mainMenu();
            } else {
                ui.displayMessage("Sorry, the username or password can not be used");
                streamSetup();
            }
    }

}
