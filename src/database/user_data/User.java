package database.user_data;

import actions.observer_design.Observer;
import database.Movie;

import java.util.ArrayList;

public class User implements Observer {
    boolean isNotified = false;
    private Credentials credentials;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Notification> notifications;
    private ArrayList<String> subscribedGenres;
    private int tokensCount;
    private int numFreePremiumMovies;

    /**
     * Constructor
     *
     * @param cr user's credentials
     */
    public User(final Credentials cr) {
        credentials = cr;
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    /**
     * Update the user (observer design pattern).
     *
     * @param notification received from the EventManager
     */
    public void update(Notification notification) {
        getNotifications().add(notification);
    }

    /**
     * Getter
     *
     * @return the variable that show if the user was notified or not
     */
    public boolean isNotified() {
        return isNotified;
    }

    /**
     * Getter
     *
     * @param notified the variable that show if the user was notified or not
     */
    public void setNotified(final boolean notified) {
        isNotified = notified;
    }

    /**
     * getter
     *
     * @return
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Getter
     *
     * @return number of tokens
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Getter
     *
     * @return the list of user's notifications
     */
    public ArrayList<Notification> getNotifications() {
        if (notifications == null) {
            setNotifications(new ArrayList<>());
            return notifications;
        }
        return notifications;
    }

    /**
     * Setter
     *
     * @param notifications the list of user's notifications
     */
    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Getter
     *
     * @return the subscribed genres
     */
    public ArrayList<String> getSubscribedGenres() {
        if (subscribedGenres == null) {
            setSubscribedGenres(new ArrayList<>());
            return subscribedGenres;
        }
        return subscribedGenres;
    }

    /**
     * Setter
     *
     * @param subscribedGenres the subscribed genres
     */
    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    /**
     * Getter
     *
     * @return number of free movies
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * Setter
     *
     * @param tokensCount number of tokens
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Setter
     *
     * @param numFreePremiumMovies number of free movies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     * Setter
     *
     * @param credentials number of credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Getter
     *
     * @return list of watched movies
     */
    public ArrayList<Movie> getWatchedMovies() {
        if (watchedMovies == null) {
            watchedMovies = new ArrayList<>();
            return watchedMovies;
        }
        return watchedMovies;
    }

    /**
     * Getter
     *
     * @return list of liked movies
     */
    public ArrayList<Movie> getLikedMovies() {
        if (likedMovies == null) {
            likedMovies = new ArrayList<>();
            return likedMovies;
        }
        return likedMovies;
    }

    /**
     * Getter
     *
     * @return list of rated movies
     */
    public ArrayList<Movie> getRatedMovies() {
        if (ratedMovies == null) {
            ratedMovies = new ArrayList<>();
            return ratedMovies;
        }
        return ratedMovies;
    }

    /**
     * Getter
     *
     * @return listo of purchased movies
     */
    public ArrayList<Movie> getPurchasedMovies() {
        if (purchasedMovies == null) {
            purchasedMovies = new ArrayList<>();
            return purchasedMovies;
        }
        return purchasedMovies;
    }

    /**
     * Setter
     *
     * @param userMovies list of purchased movies
     */
    public void setPurchasedMovies(final ArrayList<Movie> userMovies) {
        this.purchasedMovies = userMovies;
    }

    /**
     * Setter
     *
     * @param watchedMovies list of watched movies
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Setter
     *
     * @param likedMovies list of liked movies
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Setter
     *
     * @param ratedMovies list of rated movies
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
