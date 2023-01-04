package input.actions;

public class SortFilter {
    private String rating;
    private String duration;

    /**
     * getter
     * @return
     */
    public String getRating() {
        return rating;
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
     * @param rating
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * setter
     * @param duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
