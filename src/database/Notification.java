package database;

public class Notification {
    private String nameMovie;
    private String message;

    public Notification(String nameMovie, String message) {
        this.nameMovie = nameMovie;
        this.message = message;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
