package pages.unlogged_subpages;

import actions.RegisterAction;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;
import pages.HomepageLogged;
import pages.HomepageUnlogged;
import pages.Page;

public class Register extends Page {

    /**
     * constructor
     * @param output arrayNode-ul pentru output
     * @param session sesiunea curenta
     */
    public Register(final ArrayNode output, final Session session) {
        super(output, session);
    }


    /**
     * mai intai se verifica daca in input am primit o alta actiune in afara de login,
     * dupa se apeleaza metoda execute() din clasa specifica Register-ului
     */
    public void actions() {
        if (super.verifAction("register") == -1) {
            return;
        }
        if (getSession().getActionErr() == -1) {
            super.printBasicError();
            getSession().getNavigation().add("logout");
        } else {
            getSession().getNavigation().add("logged");
            super.printOnPage();
        }
    }
    /**
     * functia de move verifica primul element din coada de navigare si in functie de acesta
     * se muta pe pagina respectiva
     */
    public void move() {
        System.out.println("a ajuns la move in register se duce la" + super.getSession().getNavigation().peek());
        if (getSession().getNavigation().isEmpty()) {
            getSession().setPageCurr("register");
        } else if (getSession().getNavigation().peek().equals("register")) {
            getSession().getNavigation().remove();
            getSession().setPageCurr("register");
        } else if(getSession().getNavigation().peek().equals("logged")
                    || getSession().getNavigation().peek().equals("logout")) {
           super.navigate(getSession().getNavigation().peek());
        } else {
            getSession().getNavigation().remove();
            super.printBasicErrorPage();
        }
    }
}
