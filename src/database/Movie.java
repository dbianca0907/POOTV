package database;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private  ArrayList<String> actors;
    private  ArrayList<String> countriesBanned;
   // private ArrayList<Double> userRates;
    private HashMap<String, Double> userRates;
    private int numLikes = 0;
    private double rating = 0.00;
    private int numRatings = 0;

    /**
     * constructor
     * @param name
     * @param year
     * @param duration
     * @param genres
     * @param actors
     * @param countriesBanned
     */
    public Movie(final String name, final int year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> actors,
                 final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        userRates = new HashMap<>();
    }

    /**
     * getter
     * @return
     */
    public HashMap<String, Double> getUserRates() {
        return userRates;
    }

    /**
     * getter
     * @return
     */
    public int getNumLikes() {
        return numLikes;
    }
    /**
     * getter
     * @return
     */
    public double getRating() {
        return rating;
    }
    /**
     * getter
     * @return
     */
    public int getNumRatings() {
        return numRatings;
    }
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
     * @param numLikes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * setter
     * @param rating
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * setter
     * @param numRatings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
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
