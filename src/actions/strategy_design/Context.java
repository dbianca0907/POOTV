package actions.strategy_design;

import actions.strategy_design.Strategy;
import database.Session;

public class Context {
    private Strategy strategy;

    /**
     * Setting the strategy.
     *
     * @param strategy the type of action
     * @param session the current session
     */
    public void setStrategy(Strategy strategy, Session session) {
        this.strategy = strategy;
        this.strategy.setSession(session);
    }

    /**
     * Execute the action.
     * @return 1, -1, 0 based on the situation
     */
    public int executeStrategy() {

        return strategy.execute();
    }
}
