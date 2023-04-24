import util.FileIO;
import util.TextUI;

public class Series{
    private String title;
    private String[] genre;
    private int year;
    private int minYear;
    private int maxYear;
    private float rating;
    private int minRating;
    private int seasons;
    private int episodes;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Series(){

        genre = new String[20];

        genre[0] = "Talk-Show";
        genre[1] = "Documentary";
        genre[2] = "Crime";
        genre[3] = "Action";
        genre[4] = "Adventure";
        genre[5] = "Drama";
        genre[6] = "Comedy";
        genre[7] = "Fantasy";
        genre[8] = "Animation";
        genre[9] = "Horror";
        genre[10] = "Sci-fi";
        genre[11] = "War";
        genre[12] = "Thriller";
        genre[13] = "Mystery";
        genre[14] = "Biography";
        genre[15] = "History";
        genre[16] = "Family";
        genre[17] = "Western";
        genre[18] = "Romance";
        genre[19] = "Sport";

    }

    public void seriesMenu(){
        String inputSeries = ui.getUserInput("Please choose between following options;\n" + "1. Search for a specific series\n" + "2. Search for a genre and receive all series in this category\n" + "3. Review your list of watched series\n" + "4. Review your list of saved series");
        switch (inputSeries) {
            case "1":
                seriesSearch();
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

    public void seriesSearch(){

    }
    public void genreSearch(){

    }
    public void watchedList(){

    }
    public void savedList(){

    }



}
