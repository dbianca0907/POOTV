package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.session_data.Session;
import pages.hierarchy_of_pages.HomepageLogged;
import pages.hierarchy_of_pages.HomepageUnlogged;
import pages.hierarchy_of_pages.logged_subpages.Movies;
import pages.hierarchy_of_pages.logged_subpages.SeeDetails;
import pages.hierarchy_of_pages.logged_subpages.Upgrades;
import pages.hierarchy_of_pages.unlogged_subpages.Login;
import pages.hierarchy_of_pages.unlogged_subpages.Register;

public class PageFactory {
    /**
     * Factory for pages
     */
    public Page getPage(final String page, final ArrayNode output, final Session session) {
        if (page == null) {
            return null;
        }
        return switch (page) {
            case "login" -> new Login(output, session);
            case "register" -> new Register(output, session);
            case "logout" -> new HomepageUnlogged(output, session);
            case "logged" -> new HomepageLogged(output, session);
            case "movies" -> new Movies(output, session);
            case "see details" -> new SeeDetails(output, session);
            case "upgrades" -> new Upgrades(output, session);
            default ->  throw new IllegalStateException(
                    "Incorrect page name!");
        };
    }
}
