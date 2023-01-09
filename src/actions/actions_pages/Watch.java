package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Watch extends Strategy {
    /**
     * Verify if the movie is in the user's list of watched movies.
     *
     * @return 1, if the movie is not in the list
     *        -1, otherwise
     */
    public int findMovie() {
        String nameMovie = getSession().getNameCurrMovie();
        ArrayList<Movie> movies = getSession().getCurrentUser().getWatchedMovies();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * Verify if a movie can be watched or not
     * @return 1, if the action was completed successfully
     *         -1, if the order of actions wasn't respected or other error occurred
     *         0, if the movie was already watched
     */
    @Override
    public int execute() {
        if (getSession().getOldFeature().equals("purchase")) {
            if (findMovie() == -1) {
                getSession().setOldFeature("watch");
                return 0;
            }
            for (Movie movie : getSession().getUnbannedMovies()) {
                if (movie.getName().equals(getSession().getNameCurrMovie())) {
                    getSession().setOldFeature("watch");
                    return 1;
                }
            }
        }
        return -1;
    }
}
