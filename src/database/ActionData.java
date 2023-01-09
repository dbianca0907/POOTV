package database;

import database.user_data.Credentials;

import java.util.ArrayList;


public class ActionData {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private String rating;
    private String duration;
    private ArrayList<String> actors;
    private ArrayList<String> genre;
    private String startsWith;
    private String movie;
    private int count;
    private double rate;
    private String subscribedGenre;
    private Movie addedMovie;
    private String deletedMovie;

    /**
     * Getter
     *
     * @return the name of the movie that will be deleted (from input)
     */
    public String getDeletedMovie() {
        return deletedMovie;
    }

    /**
     * Setter
     *
     * @param deletedMovie the name of the movie that will be deleted
     */
    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    /**
     * Getter
     *
     * @return the movie that will be added (from input)
     */
    public Movie getAddedMovie() {
        return addedMovie;
    }

    /**
     * Setter
     *
     * @param addedMovie the movie that will be aded
     */
    public void setAddedMovie(final Movie addedMovie) {
        this.addedMovie = addedMovie;
    }

    /**
     * Getter
     *
     * @return the name of the subscribed genre
     */
    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    /**
     * Setter
     *
     * @param subscribedGenre the name of the subscribed genre
     */
    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    /**
     * Getter
     *
     * @return the movie's rating
     */

    public String getRating() {
        return rating;
    }

    /**
     * Setter
     *
     * @param rating the movie's rating
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * Getter
     *
     * @return movie's duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter
     *
     * @param duration movie's duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * Getter
     *
     * @return the list of actors from a movie
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Setter
     *
     * @param actors the list of actors from a movie
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Getter
     *
     * @return the movie's list of genre
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * Setter
     *
     * @param genre the movie's list of genre
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    /**
     * Getter
     *
     * @return type of the user's account
     */
    public String getType() {
        return type;
    }

    /**
     * Setter
     *
     * @param type type of the user's account
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Getter
     *
     * @return the name of a page
     */
    public String getPage() {
        return page;
    }

    /**
     * Setter
     *
     * @param page the name of a page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * Getter
     *
     * @return the feature from the input
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Setter
     *
     * @param feature the feature from the input
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * Getter
     *
     * @return the user's credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Setter
     *
     * @param credentials the user's credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Getter
     *
     * @return the string for the search action (input)
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Setter
     *
     * @param startsWith the string for the search action (input)
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * Getter
     *
     * @return the movie received from input
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Setter
     *
     * @param movie the movie received from input
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * Getter
     *
     * @return number of tokens that need to be purchased (from input)
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter
     *
     * @param count number of tokens that need to be purchased (from input)
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Getter
     *
     * @return The movie rating (from input)
     */
    public double getRate() {
        return rate;
    }

    /**
     * Setter
     *
     * @param rate The movie rating (from input)
     */
    public void setRate(final double rate) {
        this.rate = rate;
    }
}
