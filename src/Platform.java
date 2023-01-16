import actions.actions_database.Recommendation;
import actions.factory_design.ActionFactory;
import actions.strategy_design.Context;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.action_data.ActionData;
import database.DataBase;
import database.session_data.Session;
import input.Parsing;
import input.actions.ActionsInput;
import input.data.InputData;
import output.Printer;
import pages.Page;
import pages.PageFactory;

public class Platform {
    /**
     * Main platform for interaction between user and server
     */
    public static void main(final InputData input, final ArrayNode output) {

        DataBase database = new DataBase(Parsing.parseUsers(input),
                Parsing.parseMovies(input));
        Session session = new Session(database);
        session.getNavigation().add("logout");
        Page page = new Page(output, session);
        page.getSession().setPageCurr("logout");
        PageFactory factory = new PageFactory();
        ActionFactory actionFactory = new ActionFactory();
        Context context = new Context();

        Page currPage = page;

        for (ActionsInput actionInput : input.getActions()) {
            switch (actionInput.getType()) {
                case "change page" -> {
                    if (actionInput.getPage().equals("logout")
                            && currPage.getSession().getPageCurr().equals("logout")) {
                        page.getSession().getNavigation().add("wrongRefresh");
                    } else {
                        page.getSession().getNavigation().add(actionInput.getPage());
                        page.addToHistory(actionInput.getPage());
                    }
                    if (actionInput.getPage().equals("see details")) {
                        page.getSession().getAction().setMovie(actionInput.getMovie());
                    }
                    currPage.move();
                    currPage = factory.getPage(page.getSession().getPageCurr(),
                            output, page.getSession());
                }
                case "back" -> {
                    page.getSession().getAction().setType("back");
                    if (!page.getSession().getHistory().isEmpty()
                            || page.getSession().isLogged()) {
                        String backPage = page.getSession().getHistory().peek();
                        if (!page.getSession().getBackErrors().contains(backPage)) {
                            page.getSession().getHistory().pop();
                        }
                        if (page.getSession().getHistory().peek().equals("login")
                                || page.getSession().getHistory().peek().equals("register")) {
                            page.printBasicErrorPage();
                        } else {
                            String namePage = page.getSession().getHistory().peek();
                            currPage = factory.getPage(namePage, output, page.getSession());
                            currPage.move();
                        }
                    } else {
                        page.printBasicError();
                    }
                }
                case "on page" -> {
                    ActionData newAction = Parsing.parseAction(actionInput);
                    page.getSession().setAction(newAction);
                    page.getSession().setFeature(actionInput.getFeature());
                    if (actionFactory.getStrategy(actionInput.getFeature()) == null) {
                        page.printBasicErrorPage();
                    } else {
                        context.setStrategy(actionFactory.getStrategy(actionInput.getFeature()),
                                page.getSession());
                        page.getSession().setActionErr(context.executeStrategy());
                        currPage.actions();
                    }
                }
                case "database" -> {
                    ActionData newAction = Parsing.parseAction(actionInput);
                    page.getSession().setAction(newAction);
                    page.getSession().setFeature(actionInput.getFeature());
                    context.setStrategy(actionFactory.getStrategy(actionInput.getFeature()),
                            page.getSession());
                    if (context.executeStrategy() == -1) {
                        page.printBasicErrorPage();
                    }
                }
                default -> throw new IllegalStateException(
                        "Wrong type of action!");
            }
        }
        if (page.getSession().isLogged()) {
            String type = page.getSession().getCurrentUser().getCredentials().getAccountType();
            if (type.equals("premium")) {
                Recommendation recomendation = new Recommendation(page.getSession());
                recomendation.execute();
                Printer printer = Printer.getInstance();
                printer.printRecommendation(session, output);
            }
        }

    }
}
