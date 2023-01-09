package input.data;

import java.util.ArrayList;

// name, year, duration, genres, actors, countriesBanned

public final class InputMovie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    /**
     * Getter
     *
     * @return list of movie's genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Getter
     *
     * @return list of movie's actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Getter
     *
     * @return list of countries where the movie is banned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Getter
     *
     * @return movie's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return movie's year of production
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter
     *
     * @return movie's duration
     */
    public int getDuration() {
        return duration;
    }
}
