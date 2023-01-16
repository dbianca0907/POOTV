package pages.hierarchy_of_pages.logged_subpages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.Page;

public class Movies extends Page {
    /**
     * Cpnstructor
     *
     * @param session the current user session
     */
    public Movies(final ArrayNode output, final Session session) {
        super(output, session);
    }

    /**
     * Execute the specific action for this page.
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
     * Navigate between pages
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
