package actions;

import database.Movie;

public class WatchAction extends Strategy{
    @Override
    int execute() {
        if (super.getSession().getOldFeature().equals("purchase")) {
            for (Movie movie : super.getSession().getDatabase().getAllMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    super.getSession().setOldFeature("watch");
                    return 1;
                }
            }
        }
        return -1;
    }
}
