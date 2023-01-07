package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;

public class Movies extends Page {
    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public Movies(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * se apeleaza actiunile specifice in functie de feature-ul primit la input,
     * daca actiunea nu s-a realizat cu succes, sau actiunea este diferita fata de cele dispuse
     * paginii se afiseaza eroare
     */
    public void actions() {
        if (getSession().getFeature().equals("search")) {
            if (getSession().getActionErr() == -1) {
                super.printBasicError();
            } else {
                super.printOnPage();
            }
        } else if (getSession().getFeature().equals("filter")) {
            super.printOnPage();
        } else {
            super.printBasicErrorPage();
        }
    }

    /**
     * daca coada de navigare este goala, inseamna ca a ajuns la pagina dorita,
     * deci se apeleaza printul
     * daca primul element din coada de navigare este numele paginii curente, inseamna ca s-a dat
     * refresh la pagina
     * se muta in functie de primul element al cozii pe urmatoarea pagina
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            getSession().setPageCurr("movies");
            getSession().selectMovies(getSession().getDatabase());

            super.printOnPage();
        } else if (getSession().getNavigation().peek().equals("movies")) {
            getSession().getNavigation().remove();
            getSession().setPageCurr("movies");
            getSession().selectMovies(getSession().getDatabase());
            super.printOnPage();
        } else if (getSession().getNavigation().peek().equals("logout")
                    || getSession().getNavigation().peek().equals("see details")) {
            super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            super.printBasicErrorPage();
        }
    }
}
