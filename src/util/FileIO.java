package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Content.*;


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
    public void saveWatchedList(String path, User user, Movies movies, Series series) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);

            writer.write("username; watchedMovieList; watchedSeriesList; \n");

            writer.write(user.getName() + ";" + movies.getWatchedList().toString() + ";" + series.getWatchedList().toString() + "\n");


            writer.close();


        } catch (IOException e) {


        }

    }

    public void saveSavedList(String path, User user, Movies movies, Series series) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);

            writer.write("username; savedMovieList; savedSeriesList; \n");
            writer.write(user.getName() + ";" + movies.getSavedList().toString() + ";" +series.getSavedList().toString()+ "\n");


            writer.close();


        } catch (IOException e) {


        }

    }

}
