package pages.unlogged_subpages;

import backup_actions.SnapshotAction;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;
import pages.Page;

public class Login extends Page {
    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public Login(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * mai intai se verifica daca in input am primit o alta actiune in afara de login,
     * dupa se apeleaza metoda execute() din clasa specifica Login-ului
     */
    public void actions() {
       if (super.verifAction("login") == -1)
           return;
        if (getSession().getActionErr() == -1) {
            super.printBasicError();
            getSession().getHistory().push("logout");
            getSession().getNavigation().add("logout");
        } else {
            getSession().getNavigation().add("logged");
            getSession().getHistory().push("logged");
            super.printOnPage();
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
            getSession().setPageCurr("login");
        } else if (getSession().getNavigation().peek().equals("login")) {
            getSession().getNavigation().remove();
            getSession().setPageCurr("login");
        } else if (getSession().getNavigation().peek().equals("logged")
                || getSession().getNavigation().peek().equals("logout")) {
            super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            super.printBasicErrorPage();
        }
    }
    public SnapshotAction createSnapshot() {
        return new SnapshotAction(super.getSession());
    }
}
