package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;

public class Upgrades extends Page {
    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public Upgrades(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * se realizeaza actiunea de cumparare token/cont premium
     */
    public void actions() {
        if (getSession().getActionErr() == 0) {
            super.printBasicErrorPage();
        } else if (getSession().getActionErr() == -1) {
            super.printBasicError();
        }
    }
    /**
     * daca coada de navigare este goala, inseamna ca a ajuns la pagina dorita
     * daca primul element din coada de navigare este numele paginii curente, inseamna ca s-a dat
     * refresh la pagina
     * se muta in functie de primul element al cozii pe urmatoarea pagina
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            super.getSession().setPageCurr("upgrades");
        } else if (getSession().getNavigation().peek().equals("upgrades")) {
            getSession().getNavigation().remove();
            getSession().setPageCurr("upgrades");
        } else if (getSession().getNavigation().peek().equals("movies")
                    || getSession().getNavigation().peek().equals("logout")) {
          super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getNavigation().remove();
            getSession().getHistory().pop();
            super.printBasicErrorPage();
        }
    }
}
