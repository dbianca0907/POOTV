package actions.actions_database;

import actions.observer_design.EventManager;
import actions.strategy_design.Strategy;
import database.Movie;
import java.util.ArrayList;

public class Add extends Strategy {

    public int findMovie(final ArrayList<Movie> movies, String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }

    public int execute() {
        EventManager eventManager = new EventManager(super.getSession().getDatabase());
        Movie newMovie = super.getSession().getAction().getAddedMovie();
        ArrayList<Movie> allMovies = super.getSession().getDatabase().getAllMovies();

        if (findMovie(allMovies, newMovie.getName()) == -1)
            return -1;
        allMovies.add(newMovie);
        eventManager.notify("ADD", newMovie);
        return 1;
    }
}
