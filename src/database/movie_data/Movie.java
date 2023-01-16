package database.movie_data;

import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private HashMap<String, Double> userRates;
    private int numLikes = 0;
    private double rating = 0.00;
    private int numRatings = 0;

    /**
     * Constructor
     *
     * @param name movie's name
     * @param year movie's year of production
     * @param duration  movie's duration
     * @param genres movie's list of genres
     * @param actors movie's list of actors
     * @param countriesBanned movie's countries that is banned
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
     * Getter
     *
     * @return hashmap that contains the user names and their ratings for the movie
     */
    public HashMap<String, Double> getUserRates() {
        return userRates;
    }

    /**
     * Getter
     *
     * @return number of likes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * Getter
     *
     * @return the calculated rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Getter
     *
     * @return the number of ratings
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * Getter
     *
     * @return the list of genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Getter
     *
     * @return the list of actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Getter
     *
     * @return the countries where the movie is banned
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

    /**
     * Setter
     *
     * @param numLikes number of likes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Setter
     *
     * @param rating movie's calculated rating
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Setter
     *
     * @param numRatings movie's number of ratings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * Setter
     *
     * @param name movie's name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter
     *
     * @param year movie's year of production
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Setter
     *
     * @param duration movie's duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Setter
     *
     * @param genres movie's list of genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Setter
     *
     * @param actors movie's list of actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Setter
     *
     * @param countriesBanned movie's list of countries where is not allowed
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
