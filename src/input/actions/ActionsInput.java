package input.actions;

import input.data.InputCredentials;
import input.data.InputMovie;

public class ActionsInput {
    private String type;
    private String page;
    private String feature;
    private InputCredentials credentials;
    private FilterInput filters;
    private String startsWith;
    private String movie;
    private int count;
    private double rate;
    private String subscribedGenre;
    private InputMovie addedMovie;
    private String deletedMovie;

    /**
     * Getter
     *
     * @return movie's name that will be deleted
     */
    public String getDeletedMovie() {
        return deletedMovie;
    }

    /**
     * Getter
     *
     * @return the movie that will be added
     */
    public InputMovie getAddedMovie() {
        return addedMovie;
    }

    /**
     * Getter
     *
     * @return name of the subscribed genre
     */
    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    /**
     * Getter
     *
     * @return type of action
     */
    public String getType() {
        return type;
    }

    /**
     * Getter
     *
     * @return the page name
     */
    public String getPage() {
        return page;
    }

    /**
     * Getter
     *
     * @return the action's feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Getter
     *
     * @return the user credentials
     */
    public InputCredentials getCredentials() {
        return credentials;
    }

    /**
     * Getter
     *
     * @return the filters from input
     */
    public FilterInput getFilters() {
        return filters;
    }

    /**
     * Getter
     *
     * @return the string for "search" action
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Getter
     *
     * @return the movie from input
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Getter
     *
     * @return the count of tokens that need to pe purchased
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter
     *
     * @return user rate from input
     */
    public double getRate() {
        return rate;
    }
}
