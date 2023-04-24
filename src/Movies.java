import util.FileIO;
import util.TextUI;
import util.UserHandler;

import java.util.ArrayList;

public class Movies {
    private String title;
    private String[] genre;
    private int year;
    private int minYear;
    private int maxYear;
    private float rating;
    private int minRating;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Movies() {

        genre = new String[20];
        genre[0] = "Crime";
        genre[1] = "Drama";
        genre[2] = "Biography";
        genre[3] = "Sport";
        genre[4] = "History";
        genre[5] = "Romance";
        genre[6] = "War";
        genre[7] = "Mystery";
        genre[8] = "Adventure";
        genre[9] = "Family";
        genre[10] = "Fantasy";
        genre[11] = "Thriller";
        genre[12] = "Horror";
        genre[13] = "Film-Noir";
        genre[14] = "Action";
        genre[15] = "Sci-fi";
        genre[16] = "Comedy";
        genre[17] = "Musical";
        genre[18] = "Western";
        genre[19] = "Music";
    }

    public void movieMenu() {
        String inputMovies = ui.getUserInput("Please choose between following options;\n" + "1. Search for a specific movie\n" + "2. Search for a genre and recieve all movies in this category\n" + "3. Review your list of watched movies\n" + "4. Review your list of saved movies");
        switch (inputMovies) {
            case "1":
                movieSearch();
                break;
            case "2":
                genreSearch();
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


        public void movieSearch () {


            String input = ui.getUserInput("Please type the desired movie:");

            String[] moviesData = io.readMoviesData("src/Movies.csv", 100);


            for (String s : moviesData) {
                String[] line = s.split(";");
                this.title = line[0].trim();
                this.year = Integer.parseInt(line[1].trim());
                this.genre = line[2].split(".");
                this.rating = Float.parseFloat(line[3].trim());
                    if (input.equalsIgnoreCase(title)) {
                        if (ui.getUserInput("Choose between: 1/2\n" + "1. Watch the chosen movie\n" + "2. Save movie to your saved list").equalsIgnoreCase("1")) {
                            ui.displayMessage("You are now watching " + input);
                        } else {
                            //savedList.add(input);
                            System.out.println("The movie was saved on your watch list");

                        }

                    }
                }
            }



        public void genreSearch () {

        }
        public void watchedList () {

        }
        public void savedList () {

        }

    }
