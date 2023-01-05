package actions.database_actions;

import actions.Strategy;
import database.Movie;
import java.util.ArrayList;

public class Add extends Strategy {
    public int findMovie(final ArrayList<Movie> movies, String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return 1;
            }
        }
        return -1;
    }

    // de notificat
    public int execute() {
        Movie newMovie = super.getSession().getAction().getAddedMovie();
        ArrayList<Movie> allMovies = super.getSession().getDatabase().getAllMovies();

        if (findMovie(allMovies, newMovie.getName()) == -1)
            return -1;
        allMovies.add(newMovie);
        return 1;
    }
}
