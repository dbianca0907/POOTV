package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.movie_data.Movie;

import java.util.ArrayList;
import java.util.Map;

public class Rate extends Strategy {
    /**
     * Verify if the movie is in the user list of available movies.
     *
     * @return 1, if the movie is in the list
     *        -1, otherwise
     */
    public int findMovie(final ArrayList<Movie> movies) {
        String nameMovie = super.getSession().getNameCurrMovie();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * Rate a movie.
     *
     * @return 1, if the action was completed successfully
     *         -1, if the order of actions wasn't respected or other error occurred
     *         0, if the movie was already rated
     */
    @Override
    public int execute() {
        double ratingFromInput = getSession().getAction().getRate();

        if (ratingFromInput < 1
                || ratingFromInput > 5
                || findMovie(getSession().getUnbannedMovies()) == -1) {
            return -1;
        }

        if (getSession().getOldFeature().equals("watch")
            || getSession().getOldFeature().equals("like")) {
            double rate = getSession().getAction().getRate();

            for (Movie movie : getSession().getUnbannedMovies()) {
                if (movie.getName().equals(getSession().getNameCurrMovie())) {
                    /* Adding in a hashmap all the ratings for a movie.
                       The hashmap is stored using the name of the user as a key and its
                       given rating. */
                    String nameUser = getSession().getCurrentUser().getCredentials().getName();
                    movie.getUserRates().put(nameUser, rate);
                    int sum = 0;
                    for (Map.Entry<String, Double> entry : movie.getUserRates().entrySet()) {
                        sum += entry.getValue();
                    }
                    movie.setNumRatings(movie.getUserRates().size());
                    movie.setRating((double) sum / movie.getNumRatings());

                    if (findMovie(super.getSession().getCurrentUser().getRatedMovies()) == 1) {
                        return 0;
                    }
                    return 1;
                }
            }
        }
        return -1;
    }
}
