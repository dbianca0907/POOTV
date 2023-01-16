package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.movie_data.Movie;

import java.util.ArrayList;

public class Like extends Strategy {
    /**
     * Verify if the movie is in the user list of liked movies.
     *
     * @return 1, if the movie is not in the list
     *        -1, otherwise
     */

    public int findMovie() {
        String nameMovie = super.getSession().getNameCurrMovie();
        ArrayList<Movie> movies = super.getSession().getCurrentUser().getLikedMovies();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * Increasing the number of likes to a given movie and adding it to a user list of movies.
     *
     * @return 1, if the action was completed successfully
     *         -1, if the order of actions wasn't respected or any other error occurred
     *         0, if the order was respected but the movie was already liked by the user
     */
    @Override
    public int execute() {
        if (super.getSession().getOldFeature().equals("watch")
                || super.getSession().getOldFeature().equals("rate")) {
            if (findMovie() == -1) {
                super.getSession().setOldFeature("like");
                return 0;
            }
            for (Movie movie : super.getSession().getUnbannedMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    int numLikes = movie.getNumLikes() + 1;
                    movie.setNumLikes(numLikes);
                    super.getSession().setOldFeature("like");
                    return 1;
                }
            }
        }
        return -1;
    }
}
