package actions.observer_design;

import database.Notification;

public interface Observer {
    void update(Notification notif);
}
