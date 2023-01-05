package actions.pages_actions;

import actions.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Purchase extends Strategy {
    /**
     * cauta filmul in lista de filme cumparate
     *
     * @return 1, daca nu este deja cumparat
     *          -1, daca a fost cumparat de user deja
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
     * metoda pentru cumpoararea filmului
     * @return -1, daca userul premium nu mai are filme gratis si nici tokens ca sa cumpere
     *          -1, daca userul standard nu are tokens ca sa cumpere filmul
     *          1, daca actiunea s-a realizat cu succes
     */
    public int execute() {
        if (findMovie() == -1) {
            super.getSession().setOldFeature("purchase");
            return 0;
        }
        if (super.getSession().getOldFeature().equals("watch")
            || super.getSession().getOldFeature().equals("like")
            || super.getSession().getOldFeature().equals("rate"))
            return -1;
        if (super.getSession().getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            if (super.getSession().getCurrentUser().getNumFreePremiumMovies() <= 0) {
                if (super.getSession().getCurrentUser().getTokensCount() < 2) {
                    return -1;
                }
                int nrTokens = super.getSession().getCurrentUser().getTokensCount();
                super.getSession().getCurrentUser().setTokensCount(nrTokens - 2);
                super.getSession().setOldFeature("purchase");
                return 1;
            }
            int nrMoviesFree = super.getSession().getCurrentUser().getNumFreePremiumMovies();
            super.getSession().getCurrentUser().setNumFreePremiumMovies(nrMoviesFree - 1);
            super.getSession().setOldFeature("purchase");
            return 1;
        } else if (super.getSession().getCurrentUser().getCredentials().getAccountType().equals("standard")) {
            if (super.getSession().getCurrentUser().getTokensCount() < 2) {
                return -1;
            }
            int nrTokens = super.getSession().getCurrentUser().getTokensCount();
            super.getSession().getCurrentUser().setTokensCount(nrTokens - 2);
            super.getSession().setOldFeature("purchase");
            return 1;
        }
        return -1;
    }
}
