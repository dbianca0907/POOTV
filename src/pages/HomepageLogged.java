package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;

public class HomepageLogged extends Page {
    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public HomepageLogged(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * in cazul in care se acceseaza o eroare in cadrul paginii, va afisa eroare
     */
    public void actions() {
        super.printBasicErrorPage();
    }
    /**
     * daca coada de navigare este goala, inseamna ca a ajuns la pagina dorita
     * daca primul element din coada de navigare este numele paginii curente, inseamna ca s-a dat
     * refresh la pagina
     * se muta in functie de primul element al cozii pe urmatoarea pagina
     */

    // de refacut navigarea pe logged
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            getSession().setPageCurr("logged");
        } else if (getSession().getNavigation().peek().equals("logged")) {
            getSession().setPageCurr("logged");
            getSession().getNavigation().remove();
            HomepageLogged page = new HomepageLogged(super.getOutput(), super.getSession());
            page.move();
        } else if ( getSession().getNavigation().peek().equals("logout")
                || getSession().getNavigation().peek().equals("movies")
                || getSession().getNavigation().peek().equals("upgrades")) {
            super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            getSession().setPageCurr("logged");
            super.printBasicErrorPage();
        }
    }
}
