package actions;

import actions.Strategy;
import database.Session;

public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy, Session session) {
        this.strategy = strategy;
        this.strategy.setSession(session);
    }

    public int executeStrategy() {

        return strategy.execute();
    }
}
