package database;

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

    /**
     * getter
     * @return
     */
    public String getRating() {
        return rating;
    }

    /**
     * setter
     * @param rating
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * getter
     * @return
     */
    public String getDuration() {
        return duration;
    }

    /**
     * setter
     * @param duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * setter
     * @param actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * setter
     * @param genre
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    /**
     * getter
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * setter
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * getter
     * @return
     */
    public String getPage() {
        return page;
    }

    /**
     * setter
     * @param page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * getter
     * @return
     */
    public String getFeature() {
        return feature;
    }

    /**
     * setter
     * @param feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * getter
     * @return
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * setter
     * @param credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * getter
     * @return
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * setter
     * @param startsWith
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * getter
     * @return
     */
    public String getMovie() {
        return movie;
    }

    /**
     * setter
     * @param movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * getter
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * setter
     * @param count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * getter
     * @return
     */
    public double getRate() {
        return rate;
    }

    /**
     * setter
     * @param rate
     */
    public void setRate(final double rate) {
        this.rate = rate;
    }
}
