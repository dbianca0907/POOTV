package actions.actions_database;

import actions.strategy_design.Strategy;
import database.movie_data.Movie;

import java.util.ArrayList;

public class Subscribe extends Strategy {

    /**
     * Subscribing the user to a specific genre.
     *
     * @return 1, if the action was completed successfully
     *        -1, if the user is already subscribed to that genre or
     *            the lists of movies that aren't banned is empty
     */
    @Override
    public int execute() {
        String genre = getSession().getAction().getSubscribedGenre();
        ArrayList<Movie> movies = getSession().getUnbannedMovies();
        ArrayList<String> userSubscriptions = getSession().getCurrentUser().getSubscribedGenres();

        if (userSubscriptions.contains(genre)
            || movies == null) {
            return -1;
        }

        for (Movie movie : movies) {
            if (movie.getName().equals(getSession().getNameCurrMovie())) {
                if (movie.getGenres().contains(genre)) {
                    getSession().getCurrentUser().getSubscribedGenres().add(genre);
                    return 1;
                }
            }
        }

        return -1;
    }
}
