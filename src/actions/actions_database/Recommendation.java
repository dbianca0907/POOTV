package actions.actions_database;

import database.Movie;
import database.user_data.Notification;
import database.Session;

import java.util.*;

public class Recommendation {
    private Session session;
    private Map<String, Integer> likedGenres = new TreeMap<>();

    /**
     * Constructor
     *
     * @param session the current session
     */
    public Recommendation(final Session session) {
        this.session = session;
    }

    /**
     * Find the current user's most liked genre.
     *
     * @param likedMovies the list that contains the user's liked movies
     * @return the most liked genre or null if an error occurred
     */
    public String findLikedGenre(final ArrayList<Movie> likedMovies) {
        /* Adding all the genres from the user's liked movies in a
            treemap -> (genre, number of likes), the genres are sorted lexicographically */

        for (Movie movie : likedMovies) {
            ArrayList<String> genres = movie.getGenres();
            if (!genres.isEmpty()) {
                for (String genre : genres) {
                    if (!likedGenres.containsKey(genre)) {
                        likedGenres.put(genre, 0);
                    } else {
                        int increase = likedGenres.get(genre) + 1;
                        likedGenres.put(genre, increase);
                    }
                }
            }
        }

        // Sorting the number of likes and adding the values decreasingly in a list

        ArrayList<Integer> values = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : likedGenres.entrySet()) {
            values.add(entry.getValue());
        }
        Collections.sort(values, Collections.reverseOrder());

        // Getting the most liked genre

        int maxNumLikes = values.get(0);
        for (Map.Entry<String, Integer> entry : likedGenres.entrySet()) {
            if (entry.getValue() == maxNumLikes) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Finding the movie that contains the most liked genre
     * @param likedGenre the most liked genre
     * @param movies the list that contains all the unwatched movies that the user is allowed to watch
     * @return
     */
    public Movie findRecommendation(final String likedGenre, final ArrayList<Movie> movies) {
        ArrayList<Movie> sortedMovies = new ArrayList<>();
        ArrayList<Integer> likedMovies = new ArrayList<>();
        ArrayList<Movie> copy = new ArrayList<>(movies);

        // Sorting the number of likes in a decreasing order

        for (int i = 0; i < movies.size(); i++) {
            likedMovies.add(movies.get(i).getNumLikes());
        }
        Collections.sort(likedMovies, Collections.reverseOrder());

        // Ordering the movies from the list based on their number of likes

        for (int i = 0; i < likedMovies.size(); i++) {
            for (Movie movie : copy) {
                if (movie.getNumLikes() == likedMovies.get(i)) {
                    sortedMovies.add(movie);
                    copy.remove(movie);
                    break;
                }
            }
        }

        // Getting the movie that contains user's most liked genre

        for (Movie movie : sortedMovies) {
            if (movie.getGenres() != null) {
                if (movie.getGenres().contains(likedGenre)) {
                    return movie;
                }
            }
        }
        return null;
    }

    /**
     * Making the recommendation for the logged user.
     */
    public void execute() {
        ArrayList<Movie> likedMovies = getSession().getCurrentUser().getLikedMovies();
        Notification notify;
        String mostLikedGenre;

        // getting the most liked genre

        if (!likedMovies.isEmpty()) {
            mostLikedGenre = findLikedGenre(getSession().getCurrentUser().getLikedMovies());
        } else {
            notify = new Notification("No recommendation",
                    "Recommendation");
            getSession().getCurrentUser().update(notify);
            return;
        }
        // removing all the watched movies from the list of unbanned movies
        ArrayList<Movie> unbannedMovies = getSession().getUnbannedMovies();
        ArrayList<Movie> watchedMovies = getSession().getCurrentUser().getWatchedMovies();

        for (Movie movie : watchedMovies) {
            for (Movie movie1 : unbannedMovies) {
                if (movie.getName().equals(movie1.getName())) {
                    unbannedMovies.remove(movie1);
                    break;
                }
            }
        }

        // getting the movie for recommendation

        Movie recommendation = findRecommendation(mostLikedGenre, unbannedMovies);

        if (recommendation == null) {
            notify = new Notification("No recommendation",
                                            "Recommendation");
        } else {
            notify = new Notification(recommendation.getName(),
                    "Recommendation");
        }
        getSession().getCurrentUser().update(notify);
    }

    /**
     * Getter the current session.
     *
     * @return the current session
     */
    public Session getSession() {
        return session;
    }
}


