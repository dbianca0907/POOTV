package database;

import java.util.ArrayList;
public class User {
   private Credentials credentials;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private int tokensCount;
    private int numFreePremiumMovies;

    /**
     * constructor
     * @param cr
     */
    public User(Credentials cr) {
        credentials = cr;
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    /**
     * getter
     * @return
     */
    public Credentials getCredentials() {
        return credentials;
    }
    /**
     * getter
     * @return
     */
    public int getTokensCount() {
        return tokensCount;
    }
    /**
     * getter
     * @return
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     * setter
     * @param tokensCount
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * setter
     * @param numFreePremiumMovies
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
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
    public ArrayList<Movie> getWatchedMovies() {
        if (watchedMovies == null) {
            watchedMovies = new ArrayList<>();
            return watchedMovies;
        }
        return watchedMovies;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getLikedMovies() {
        if (likedMovies == null) {
            likedMovies = new ArrayList<>();
            return likedMovies;
        }
        return likedMovies;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getRatedMovies() {
        if (ratedMovies == null) {
            ratedMovies = new ArrayList<>();
            return ratedMovies;
        }
        return ratedMovies;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getPurchasedMovies() {
        if (purchasedMovies == null) {
            purchasedMovies = new ArrayList<>();
            return purchasedMovies;
        }
        return purchasedMovies;
    }

    /**
     * setter
     * @param userMovies
     */
    public void setPurchasedMovies(final ArrayList<Movie> userMovies) {
        this.purchasedMovies = userMovies;
    }

    /**
     * setter
     * @param watchedMovies
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * setter
     * @param likedMovies
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * setter
     * @param ratedMovies
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
