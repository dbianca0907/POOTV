package pages;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;
import pages.unlogged_subpages.Login;
import pages.unlogged_subpages.Register;

public class PageFactory {
    /**
     * factory pentru pagini
     * @param page numele paginii
     * @param output output primit din Main
     * @param session sesiunea curenta
     * @return
     */
    public Page getPage(final String page, final ArrayNode output, final Session session) {
        if (page == null)
            return null;
        switch (page) {
            case "login":
                return new Login(output, session);
            case "register":
                return new Register(output, session);
            case "logout":
                return new HomepageUnlogged(output,session);
            case "logged":
                return new HomepageLogged(output, session);
            case "movies":
                return new Movies(output, session);
            case "see details":
                return new SeeDetails(output, session);
            case "upgrades":
                return new Upgrades(output, session);
        }
        return null;
    }
}
