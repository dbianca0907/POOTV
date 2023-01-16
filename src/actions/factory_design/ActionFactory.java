package actions.factory_design;

import actions.actions_database.Add;
import actions.actions_database.Delete;
import actions.actions_database.Subscribe;
import actions.actions_pages.Like;
import actions.actions_pages.Register;
import actions.actions_pages.Search;
import actions.actions_pages.Filter;
import actions.actions_pages.Login;
import actions.actions_pages.Buy;
import actions.actions_pages.Rate;
import actions.actions_pages.Purchase;
import actions.actions_pages.Watch;

import actions.strategy_design.Strategy;

public class ActionFactory {
    /**
     * Factory design pattern for all the actions
     * @param action the name of the action
     * @return the reference to a created object
     */
    public Strategy getStrategy(final String action) {
        if (action == null) {
            return null;
        }
        return switch (action) {
            case "login" -> new Login();
            case "register" -> new Register();
            case "search" -> new Search();
            case "filter" -> new Filter();
            case "buy tokens", "buy premium account" -> new Buy();
            case "like" -> new Like();
            case "rate" -> new Rate();
            case "purchase" -> new Purchase();
            case "watch" -> new Watch();
            case "delete" -> new Delete();
            case "add" -> new Add();
            case "subscribe" -> new Subscribe();
            default -> throw new IllegalStateException(
                    "The action can not be executed");
        };
    }

}
