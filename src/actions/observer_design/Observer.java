package actions.observer_design;

import database.user_data.Notification;

public interface Observer {
    /**
     * Updating the observer.
     *
     * @param notification received from the EventManager
     */
    void update(Notification notification);
}
