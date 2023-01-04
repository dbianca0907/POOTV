package backup_actions;

import database.Session;
import pages.Page;

public class SnapshotAction {
    private Session session;

    public SnapshotAction(Session oldSession) {
        setSession(oldSession);
    }

    public void restore(Page page) {
        page.setSession(getSession());
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
