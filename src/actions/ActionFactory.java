package actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Session;
import pages.*;
import pages.unlogged_subpages.Login;
import pages.unlogged_subpages.Register;

public class ActionFactory {
    public Strategy getStrategy(final String action) {
        if (action == null)
            return null;
        switch (action) {
            case "login":
                return new LoginAction();
            case "register":
                return new RegisterAction();
            case "search":
                return new SearchAction();
            case "filter":
                return new FilterAction();
            case "buy tokens":
            case "buy premium account":
                return new BuyAction();
            case "like":
                return new LikeAction();
            case "rate":
                return new RateAction();
            case "purchase":
                return new PurchaseAction();
            case "watch":
                return new WatchAction();
        }
        return null;
    }

}
