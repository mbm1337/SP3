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

    public Series(String title, int year, String[] genre, float rating, int seasons, int episodes){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.seasons = seasons;
        this.episodes = episodes;

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



}
