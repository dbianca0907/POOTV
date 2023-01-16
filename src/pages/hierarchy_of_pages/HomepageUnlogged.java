package pages.hierarchy_of_pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.Page;

public class HomepageUnlogged extends Page {
    /**
     * Constructor
     *
     * @param session current user session
     */
    public HomepageUnlogged(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific action for this page
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
     * Navigate between pages
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
