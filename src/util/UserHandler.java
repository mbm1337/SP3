package util;

import java.util.ArrayList;

public class UserHandler {
    private ArrayList<User> users = new ArrayList<>();



    public boolean login(String username, String password) {
        for (User user : users){
            if(username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
                users.add(user);
                return true;
            }
        }
        return false;
    }

    public boolean createUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password));
        return true;
    }
}