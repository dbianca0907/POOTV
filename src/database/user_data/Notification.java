package database.user_data;

public class Notification {
    private String nameMovie;
    private String message;

    /**
     * Constructor
     *
     * @param nameMovie movie's name or "no recommendation"
     * @param message notification's message
     */
    public Notification(final String nameMovie, final String message) {
        this.nameMovie = nameMovie;
        this.message = message;
    }

    /**
     * Getter
     *
     * @return movie's name
     */
    public String getNameMovie() {
        return nameMovie;
    }

    /**
     * Getter
     *
     * @return notification's message
     */
    public String getMessage() {
        return message;
    }
}
