package pages.hierarchy_of_pages.logged_subpages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.Page;

public class Upgrades extends Page {
    /**
     * Constructor
     *
     * @param session the current user session
     */
    public Upgrades(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific action for this page.
     */
    public void actions() {
        if (getSession().getActionErr() == 0) {
            super.printBasicErrorPage();
        } else if (getSession().getActionErr() == -1) {
            super.printBasicError();
        }
    }

    /**
     * Navigate between pages
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
