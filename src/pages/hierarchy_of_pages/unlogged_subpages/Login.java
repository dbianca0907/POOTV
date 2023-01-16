package pages.hierarchy_of_pages.unlogged_subpages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.Page;

public class Login extends Page {
    /**
     * Constructor
     *
     * @param session the current user session
     */
    public Login(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific action for this page.
     */
    public void actions() {
        if (super.verifyAction("login") == -1) {
            return;
        }
        if (getSession().getActionErr() == -1) {
            super.printBasicError();
            getSession().getHistory().push("logout");
            getSession().getNavigation().add("logout");
        } else {
            getSession().getNavigation().add("logged");
            getSession().getHistory().push("logged");
            printOnPage();
        }
    }

    /**
     * Navigate through pages
     */
    public void move() {
        if (getSession().getNavigation().isEmpty()) {
            getSession().setPageCurr("login");
        } else if (getSession().getNavigation().peek().equals("login")) {
            getSession().getNavigation().remove();
            getSession().setPageCurr("login");
        } else if (getSession().getNavigation().peek().equals("logged")
                || getSession().getNavigation().peek().equals("logout")) {
            navigate(getSession().getNavigation().peek());
        } else {
            getSession().getHistory().pop();
            getSession().getNavigation().remove();
            printBasicErrorPage();
        }
    }
}
