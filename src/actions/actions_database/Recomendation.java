package actions.actions_database;

import database.Movie;
import database.Notification;
import database.Session;

import java.util.*;

public class Recomendation {
    Session session;
    Map<String, Integer> likedGenres = new HashMap<>();

    public Recomendation(Session session) {
        this.session = session;
    }

    public String findLikedGenre(final ArrayList<Movie> likedMovies) {
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
        TreeMap<String, Integer> sorted = new TreeMap<>();
        sorted.putAll(likedGenres);

        ArrayList<Integer> values = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : likedGenres.entrySet()) {
            values.add(entry.getValue());
        }
        Collections.sort(values, Collections.reverseOrder());
        int maxNumLikes = values.get(0);
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            if (entry.getValue() == maxNumLikes) {
                return entry.getKey();
            }
        }
        return "nothing";
    }

    public Movie findRecomendation(String likedGenre, ArrayList<Movie> movies) {
        ArrayList<Movie> sortedMovies = new ArrayList<>();
        ArrayList<Integer> likedMovies = new ArrayList<>();
        ArrayList<Movie> copy = new ArrayList<>(movies);

        for (int i = 0; i < movies.size(); i++) {
            likedMovies.add(movies.get(i).getNumLikes());
        }
        Collections.sort(likedMovies, Collections.reverseOrder());

        for (int i = 0; i < likedMovies.size(); i++) {
            for (Movie movie : copy) {
                if (movie.getNumLikes() == likedMovies.get(i)) {
                    sortedMovies.add(movie);
                    copy.remove(movie);
                    break;
                }
            }
        }
        for (Movie movie : sortedMovies) {
            if (movie.getGenres() != null) {
                if (movie.getGenres().contains(likedGenre)) {
                    return movie;
                }
            }
        }
        return null;
    }

    public void execute() {
        ArrayList<Movie> likedMovies = getSession().getCurrentUser().getLikedMovies();
        Notification notif;
        String mostLikedGenre;

        if (!likedMovies.isEmpty()) {
            mostLikedGenre = findLikedGenre(getSession().getCurrentUser().getLikedMovies());
        } else {
            notif = new Notification("No recommendation",
                    "Recommendation");
            getSession().getCurrentUser().update(notif);
            return;
        }
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
        Movie recomendation = findRecomendation(mostLikedGenre, unbannedMovies);

        if (recomendation == null) {
            notif = new Notification("No recommendation",
                                            "Recommendation");
        } else {
            notif = new Notification(recomendation.getName(),
                    "Recommendation");
        }
        getSession().getCurrentUser().update(notif);
    }

    public Session getSession() {
        return session;
    }
}


