package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Movie;
import database.Session;

public class SeeDetails extends Page{
    private String order = "nothing";

    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public SeeDetails(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * dupa ce este gasit filmul in lista curenta, in functie de nume, se verifica numele actiunii
     * din input pentru a executa actiunea specifica
     * ordinea actiunilor este realizata prin retinerea in order a numelui actiunii realizate
     * in cadrul see details
     */
    // de refacut order-ul actiunilor, de introdus in functie
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
            if ( getSession().getActionErr() != -1) {
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
        super.printBasicErrorPage();
    }

    /**
     * se verifica daca exista filmul curent in currentList
     */
    public int verifNameMovie() {
        for (Movie movie : getSession().getCurrentMovieList()) {
            if (movie.getName().equals(super.getSession().getAction().getMovie())) {
                getSession().setNameCurrMovie(super.getSession().getAction().getMovie());
                super.printOneMovie(movie);
                return 1;
            }
        }
        super.printBasicErrorPage();
        return 0;
    }

    /**
     * daca coada de navaigare este goala, s-a ajuns la pagina dorita si se verifica daca exista
     * filmul alea carui detalii trebuie afisate
     * daca numele primului element este "see details", inseamna ca s-a dat refresh la pagina
     * in functie de primul element al cozii de navigare se continua mutarea prin pagini
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            if (verifNameMovie() == 1) {
                getSession().setPageCurr("see details");
                getSession().setOldFeature("nothing");
            }
        } else if (getSession().getNavigation().peek().equals("see details")) {
            getSession().getNavigation().remove();
            if (verifNameMovie() == 1) {
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

    /**
     * getter
     * @return
     */
    public String getOrder() {
        return order;
    }

    /**
     * setter
     * @param order
     */
    public void setOrder(final String order) {
        this.order = order;
    }
}
