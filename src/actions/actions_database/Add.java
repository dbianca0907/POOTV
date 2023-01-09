package actions.actions_database;

import actions.observer_design.EventManager;
import actions.strategy_design.Strategy;
import database.Movie;
import java.util.ArrayList;

public class Add extends Strategy {

    /**
     * Search in the given list a movie, based on its name.
     *
     * @param movies the given list of movies, in this case the list with all the movies from Database
     * @param nameMovie the name of the movie received from input
     * @return -1, if the movie was found in the list (error)
     *          1, otherwise
     */

    public int findMovie(final ArrayList<Movie> movies, final String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * Add the new movie in the Database and notifying the subscribers.
     *
     * @return -1, if the movie is already in the Database
     *          1, if the action was completed successfully
     */
    @Override
    public int execute() {
        EventManager eventManager = new EventManager(super.getSession().getDatabase());
        Movie newMovie = super.getSession().getAction().getAddedMovie();
        ArrayList<Movie> allMovies = super.getSession().getDatabase().getAllMovies();
        if (findMovie(allMovies, newMovie.getName()) == -1) {
            return -1;
        }
        allMovies.add(newMovie);
        eventManager.notify("ADD", newMovie);
        return 1;
    }
}
