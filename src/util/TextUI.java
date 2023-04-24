package util;
import java.sql.SQLOutput;
import java.util.Scanner;


public class TextUI {
    Scanner scan;
    UserHandler userHandler;

    public TextUI(UserHandler userHandler) {
        this.userHandler = userHandler;
        this.scan = new Scanner(System.in);
    }

    public String getUserInput(String msg) {
        displayMessage(msg);
        String input = scan.nextLine();
        return input;
    }


    public void displayMessage(String msg) {
        System.out.println(msg);
    }


}