package pages.hierarchy_of_pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.Page;

public class HomepageLogged extends Page {

    public HomepageLogged(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific action for this page.
     * "Logged" page doesn't have an action
     */
    public void actions() {
        super.printBasicErrorPage();
    }

    /**
     * Navigate between pages
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            getSession().setPageCurr("logged");
        } else if (getSession().getNavigation().peek().equals("logged")) {
            getSession().setPageCurr("logged");
            getSession().getNavigation().remove();
            HomepageLogged page = new HomepageLogged(super.getOutput(), super.getSession());
            page.move();
        } else if (getSession().getNavigation().peek().equals("logout")
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
