import actions.actions_database.Recomendation;
import actions.factory_design.ActionFactory;
import actions.observer_design.EventManager;
import actions.strategy_design.Context;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.ActionData;
import database.DataBase;
import database.Session;
import input.Parsing;
import input.actions.ActionsInput;
import input.data.InputData;
import output.Printer;
import pages.Page;
import pages.PageFactory;

public class Platform {
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

      for(ActionsInput actionInput : input.getActions()) {
         if (actionInput.getType().equals("change page")) {
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
         } else if (actionInput.getType().equals("back")) {
             page.getSession().getAction().setType("back");
             if (!page.getSession().getHistory().isEmpty()
                 || page.getSession().isLogged()) {
                 if (!page.getSession().isBackErr()) {
                     page.getSession().getHistory().pop();
                 }
                 if (page.getSession().getHistory().peek().equals("login")
                         || page.getSession().getHistory().peek().equals("register")) {
                     page.getSession().setBackErr(true);
                     page.printBasicErrorPage();
                 } else {
                     page.getSession().setBackErr(false);
                     String namePage = page.getSession().getHistory().peek();
                     currPage = factory.getPage(namePage,output, page.getSession());
                     currPage.move();
                 }
             } else {
                 page.printBasicError();
             } // la sfarsit de structurat else-ul si testat, codul dublat
         } else if (actionInput.getType().equals("on page")) {
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
         } else if (actionInput.getType().equals("database")) {
             ActionData newAction = Parsing.parseAction(actionInput);
             page.getSession().setAction(newAction);
             page.getSession().setFeature(actionInput.getFeature());
             context.setStrategy(actionFactory.getStrategy(actionInput.getFeature()),
                     page.getSession());
             if (context.executeStrategy() == -1) {
                 page.printBasicErrorPage();
             }
         }
      }
      if (page.getSession().isLogged()) {
          String accountType = page.getSession().getCurrentUser().getCredentials().getAccountType();
          if (accountType.equals("premium")) {
              Recomendation recomendation = new Recomendation(page.getSession());
              recomendation.execute();
              Printer printer = Printer.getInstance();
              printer.printRecomendation(session, output);
          }
      }

    }
}
