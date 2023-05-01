package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class  FileIO {
    File file;
    Scanner scan;

    public ArrayList<String> readUserData(String path) {

        file = new File(path);
        ArrayList<String> data = new ArrayList<>();


        try {
            scan = new Scanner(file);

            //scan.nextLine(); // ignore header in csv

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");

        }

        return data;
    }

    public String[] readMoviesData(String path, int length) {

        file = new File(path);
        String[] arr = new String[length];
        int counter = 0;
        try {
            scan = new Scanner(file);
            scan.nextLine(); // ignore header in csv

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                arr[counter] = line;
                counter++;

            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");

        }
        return arr;

    }
    public void saveWatchedList(String path, User u) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write("username; watchedList; \n");
            writer.write(u.getName() + ";" + u.getWatchedList().toString().replace("[","").replace("]","") + ";" + "\n");
            writer.close();


        } catch (IOException e) {

        }
    }

    public void saveSavedList(String path, User u) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write("username; savedList; \n");
                writer.write(u.getName() + ";" + u.getSavedList().toString().replace("[","").replace("]","") + ";" + "\n");
            writer.close();

        } catch (IOException e) {
        }
    }

    public ArrayList<String> loadList(String path, User user) {

        file = new File(path);
        ArrayList<String> listData = new ArrayList<>();

        try {
            scan = new Scanner(file);

            scan.nextLine(); // ignore header in csv

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] line2 =  line.split(";");

                if (line2[0].equalsIgnoreCase(user.getName())) {
                    String[] movies = line2[1].split(",");
                    for (String s : movies) {
                        listData.add(s);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");

        }

        return listData;
    }

}
