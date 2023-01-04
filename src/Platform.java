import actions.ActionFactory;
import actions.Context;
import backup_actions.CommandAction;
import backup_actions.SnapshotAction;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.ActionData;
import database.DataBase;
import database.Session;
import input.Parsing;
import input.actions.ActionsInput;
import input.data.InputData;
import pages.Page;
import pages.PageFactory;

import java.util.HashMap;

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
             }
              if (actionInput.getPage().equals("see details")) {
                  page.getSession().getAction().setMovie(actionInput.getMovie());
              }
              currPage.move();
              currPage = factory.getPage(page.getSession().getPageCurr(),
                                            output, page.getSession());
         } else if (actionInput.getType().equals("on page")) {
             System.out.println("actiune curenta" + actionInput.getFeature());
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
              System.out.println(page.getSession().getOldFeature());
         }
      }

    }
}
