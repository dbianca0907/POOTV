package actions;

import actions.database_actions.Add;
import actions.database_actions.Delete;
import actions.pages_actions.*;

public class ActionFactory {
    public Strategy getStrategy(final String action) {
        if (action == null)
            return null;
        switch (action) {
            case "login":
                return new Login();
            case "register":
                return new Register();
            case "search":
                return new Search();
            case "filter":
                return new Filter();
            case "buy tokens":
            case "buy premium account":
                return new Buy();
            case "like":
                return new Like();
            case "rate":
                return new Rate();
            case "purchase":
                return new Purchase();
            case "watch":
                return new Watch();
            case "delete":
                return new Delete();
            case "add":
                return new Add();
        }
        return null;
    }

}
