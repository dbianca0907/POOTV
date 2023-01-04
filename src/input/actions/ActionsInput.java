package input.actions;
import input.data.InputCredentials;

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

    /**
     * getter
     * @return
     */
    public String getType() {
        return type;
    }
    /**
     * getter
     * @return
     */
    public String getPage() {
        return page;
    }
    /**
     * getter
     * @return
     */
    public String getFeature() {
        return feature;
    }
    /**
     * getter
     * @return
     */
    public InputCredentials getCredentials() {
        return credentials;
    }
    /**
     * getter
     * @return
     */
    public FilterInput getFilters() {
        return filters;
    }
    /**
     * getter
     * @return
     */
    public String getStartsWith() {
        return startsWith;
    }
    /**
     * getter
     * @return
     */
    public String getMovie() {
        return movie;
    }
    /**
     * getter
     * @return
     */
    public int getCount() {
        return count;
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
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * setter
     * @param page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * setter
     * @param feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * setter
     * @param credentials
     */
    public void setCredentials(final InputCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * setter
     * @param movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * setter
     * @param count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * setter
     * @param rate
     */
    public void setRate(final double rate) {
        this.rate = rate;
    }
}
