package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;
import pages.unlogged_subpages.Login;
import pages.unlogged_subpages.Register;

public class HomepageUnlogged extends Page {
    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public HomepageUnlogged(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * singura actiune care se realizeaza este setarea sesiunii isLogged = false,
     * stim ca user si-a dat logout
     */
    public void actions() {
        super.getSession().setLogged(false);
        super.getSession().getHistory().clear();
        super.getSession().getHistory().push("logout");
        if (!super.getSession().getNavigation().isEmpty()) {
            String namePage = super.getSession().getNavigation().peek();
            super.getSession().getHistory().push(namePage);
        }
    }

    /**
     * daca coada de navigare este goala, inseamna ca a ajuns la pagina dorita
     * daca primul element din coada de navigare este numele paginii curente, inseamna ca s-a dat
     * refresh la pagina
     * se muta in functie de primul element al cozii pe urmatoarea pagina
     */
    public void move() {
        if (super.getSession().getNavigation().isEmpty()) {
          actions();
          getSession().setPageCurr("logout");
        } else if (super.getSession().getNavigation().peek().equals("login")
                    || super.getSession().getNavigation().peek().equals("register")) {
            actions();
            super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            getSession().setPageCurr("logout");
            super.printBasicErrorPage();
        }
    }
}
