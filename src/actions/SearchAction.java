package actions;

import database.Movie;
import database.Session;

public class SearchAction extends Strategy {

    /**
     * cauta filmul care incepe cu string-ul primit la output
     * @return -1, daca filmul nu a fost gasit
     *          1, daca filmul a fost gasit si este adaugat in campul specific din session
     */
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
