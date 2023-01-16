package pages.hierarchy_of_pages.logged_subpages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.movie_data.Movie;
import database.session_data.Session;
import pages.Page;

public class SeeDetails extends Page {
    /**
     * Constructor
     *
     * @param session the current user session
     */
    public SeeDetails(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific actions for this page.
     */
    public void actions() {
        String nameMovie = super.getSession().getNameCurrMovie();
        Movie movie = super.findMovieInData(getSession().getDatabase().getAllMovies(), nameMovie);
        if (movie == null) {
            super.printBasicErrorPage();
            return;
        }
        if (getSession().getFeature().equals("purchase")) {
            if (getSession().getActionErr() != -1) {
                if (getSession().getActionErr() == 1) {
                    getSession().getCurrentUser().getPurchasedMovies().add(movie);
                    super.printOneMovie(movie);
                } else {
                    super.printBasicErrorPage();
                }
                return;
            }
        }
        if (getSession().getFeature().equals("watch")) {
            if (getSession().getActionErr() != -1) {
                if (getSession().getActionErr() == 1) {
                    getSession().getCurrentUser().getWatchedMovies().add(movie);
                }
                super.printOneMovie(movie);
                return;
            }
        }
        if (getSession().getFeature().equals("like")) {
            if (getSession().getActionErr() != -1) {
                if (getSession().getActionErr() == 1) {
                    getSession().getCurrentUser().getLikedMovies().add(movie);
                }
                super.printOneMovie(movie);
                return;
            }
        }
        if (getSession().getFeature().equals("rate")) {
            if (getSession().getActionErr() != -1) {
                if (getSession().getActionErr() == 1) {
                    getSession().getCurrentUser().getRatedMovies().add(movie);
                }
                super.printOneMovie(movie);
                return;
            }
        }
        if (getSession().getFeature().equals("subscribe")
                && getSession().getActionErr() == 1) {
            return;
        }
        super.printBasicErrorPage();
    }

    /**
     * Verify if the movie from input exists in the current list of movies.
     *
     * @return 1, and printing the movie if it is found in the list
     * -1, otherwise
     */
    public int verifyNameMovie() {
        if (!getSession().getCurrentMovieList().isEmpty()) {
            for (Movie movie : getSession().getCurrentMovieList()) {
                if (movie.getName().equals(super.getSession().getAction().getMovie())) {
                    super.printOneMovie(movie);
                    return 1;
                }
            }
        }
        getSession().getHistory().pop();
        super.printBasicErrorPage();
        return 0;
    }

    /**
     * Navigate between pages
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            getSession().setNameCurrMovie(super.getSession().getAction().getMovie());
            if (verifyNameMovie() == 1) {
                getSession().setPageCurr("see details");
                getSession().setOldFeature("nothing");
            }
        } else if (getSession().getNavigation().peek().equals("see details")) {
            getSession().setNameCurrMovie(super.getSession().getAction().getMovie());
            getSession().getNavigation().remove();
            if (verifyNameMovie() == 1) {
                getSession().setPageCurr("see details");
                getSession().setOldFeature("nothing");
            }
        } else if (getSession().getNavigation().peek().equals("movies")
                || getSession().getNavigation().peek().equals("logout")
                || getSession().getNavigation().peek().equals("upgrades")) {

            super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            super.printBasicErrorPage();
        }
    }
}
