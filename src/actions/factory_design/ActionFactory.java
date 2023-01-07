package actions.factory_design;

import actions.actions_database.Add;
import actions.actions_database.Delete;
import actions.actions_database.Subscribe;
import actions.actions_pages.*;
import actions.strategy_design.Strategy;

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
            case "subscribe":
                return new Subscribe();
        }
        return null;
    }

}
