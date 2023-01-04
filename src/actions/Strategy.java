package actions;

import database.Session;

public abstract class Strategy {
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    abstract int execute();


}
