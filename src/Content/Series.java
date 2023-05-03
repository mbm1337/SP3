package Content;

import java.util.ArrayList;
import java.util.List;

public class Series {
    private int id;
    private String title;
    private List<String> genre;

    private List<Integer> year;
    private float rating;
    private int seasons;
    private int episodes;

    public Series(int id, String title, List<Integer> year, List<String> genres, float rating) {
        this.id = id;
        this.title = title;
        this.genre = genres;
        this.year = year;
        this.rating = rating;
        this.seasons = seasons;
        this.episodes = episodes;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }
    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
}