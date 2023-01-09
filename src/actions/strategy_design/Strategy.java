package actions.strategy_design;

import database.Session;

public abstract class Strategy {
    private Session session;

    /**
     * Setting the session for the strategy design pattern.
     *
     * @param session the current session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Getter
     *
     * @return the current session
     */
    public Session getSession() {
        return session;
    }

    /**
     * The common method for all the actions.
     *
     * @return 1, -1, 0, based on the situation
     */
    public abstract int execute();

}
