package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Like extends Strategy {
    /**
     * cauta filmul in lista de filme carora li s-au dat rate
     *
     * @return 1, daca nu este deja cumparat
     *        -1, daca a fost cumparat de user deja
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
     * metoda care creste numarul de like - uri al filmului din lista generala din DataBase
     */
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
