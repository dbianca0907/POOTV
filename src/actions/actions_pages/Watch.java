package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Watch extends Strategy {
    /** cauta filmul in lista de filme vazute
     *
     * @return 1, daca nu este deja cumparat
     *          -1, daca a fost cumparat de user deja
     */
    public int findMovie() {
        String nameMovie = super.getSession().getNameCurrMovie();
        ArrayList<Movie> movies = super.getSession().getCurrentUser().getWatchedMovies();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }
    @Override
    public int execute() {
        if (super.getSession().getOldFeature().equals("purchase")) {
            if (findMovie() == -1) {
                super.getSession().setOldFeature("watch");
                return 0;
            }
            for (Movie movie : super.getSession().getUnbannedMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    super.getSession().setOldFeature("watch");
                    return 1;
                }
            }
        }
        return -1;
    }
}
