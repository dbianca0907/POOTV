package actions.strategy_design;

import actions.strategy_design.Strategy;
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
