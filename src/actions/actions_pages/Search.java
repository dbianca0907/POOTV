package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

public class Search extends Strategy {

    /**
     * Search a specific movie in the user's current list of movies.
     *
     * @return 1, if the action was completed successfully
     *        -1, other error occurred
     */
    @Override
    public int execute() {
        int found = -1;
        for (Movie movie : getSession().getCurrentMovieList()) {
            if (movie.getName().startsWith(getSession().getAction().getStartsWith())) {
                getSession().getStartWithMovies().add(movie);
                found = 1;
            }
        }
        return found;
    }
}
