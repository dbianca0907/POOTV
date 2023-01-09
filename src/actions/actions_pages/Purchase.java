package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Purchase extends Strategy {
    /**
     * Verify if the movie is in the user's list of purchased movies.
     *
     * @return 1, if the movie is not in the list
     *        -1, otherwise
     */
    public int findMovie() {
        String nameMovie = super.getSession().getNameCurrMovie();
        ArrayList<Movie> movies = super.getSession().getCurrentUser().getPurchasedMovies();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * Purchase a new film.
     *
     * @return 1, if the action was completed successfully
     *         -1, if the order of actions wasn't respected or other error occurred
     *         0, if the movie was already purchased
     */
    @Override
    public int execute() {
        if (findMovie() == -1) {
            getSession().setOldFeature("purchase");
            return 0;
        }
        if (getSession().getOldFeature().equals("watch")
            || getSession().getOldFeature().equals("like")
            || getSession().getOldFeature().equals("rate")) {
            return -1;
        }

        String accountType = getSession().getCurrentUser().getCredentials().getAccountType();

        if (accountType.equals("premium")) {
            if (getSession().getCurrentUser().getNumFreePremiumMovies() <= 0) {
                if (getSession().getCurrentUser().getTokensCount() >= 2) {
                    int nrTokens = getSession().getCurrentUser().getTokensCount();
                    getSession().getCurrentUser().setTokensCount(nrTokens - 2);
                    getSession().setOldFeature("purchase");
                    return 1;
                }
            }
            int nrMoviesFree = getSession().getCurrentUser().getNumFreePremiumMovies();
            getSession().getCurrentUser().setNumFreePremiumMovies(nrMoviesFree - 1);
            getSession().setOldFeature("purchase");
            return 1;
        } else if (accountType.equals("standard")) {
            if (getSession().getCurrentUser().getTokensCount() >= 2) {
                int nrTokens = getSession().getCurrentUser().getTokensCount();
                getSession().getCurrentUser().setTokensCount(nrTokens - 2);
                getSession().setOldFeature("purchase");
                return 1;
            }
        }
        return -1;
    }
}
