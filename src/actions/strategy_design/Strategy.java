package actions.strategy_design;

import database.session_data.Session;

public abstract class Strategy {
    private Session session;

    /**
     * Setting the session for the strategy design pattern.
     *
     * @param session the current session
     */
    public void setSession(final Session session) {
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
