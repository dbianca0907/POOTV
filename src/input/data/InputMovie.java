package input.data;
import java.util.ArrayList;

// name, year, duration, genres, actors, countriesBanned

public final class InputMovie {
    private String name;
    private int year;
    private int duration;
    private  ArrayList<String> genres;
    private  ArrayList<String> actors;
    private  ArrayList<String> countriesBanned;

    /**
     * getter
     * @return
     */
    public  ArrayList<String> getGenres() {
        return genres;
    }
    /**
     * getter
     * @return
     */
    public  ArrayList<String> getActors() {
        return actors;
    }
    /**
     * getter
     * @return
     */
    public  ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }
    /**
     * getter
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * getter
     * @return
     */
    public int getYear() {
        return year;
    }
    /**
     * getter
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * setter
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * setter
     * @param year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * setter
     * @param duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * setter
     * @param genres
     */
    public  void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * setter
     * @param actors
     */
    public  void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * setter
     * @param countriesBanned
     */
    public  void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
