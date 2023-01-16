package actions.observer_design;

import database.DataBase;
import database.movie_data.Movie;
import database.user_data.Notification;
import database.user_data.User;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
    private DataBase dataBase;
    private HashMap<String, ArrayList<User>> subscribers = new HashMap<>();

    /**
     * Creating the hashmap "subscribers" for managing the users' subscriptions.
     *
     * @param dataBase the Database of the server
     */
    public EventManager(final DataBase dataBase) {
        this.dataBase = dataBase;
        ArrayList<User> allUsers = dataBase.getUsers();
        for (User user : allUsers) {
            // verify if the user subscribed to any genre
            ArrayList<String> subscriptions = user.getSubscribedGenres();
            if (subscriptions != null) {
                for (String genre : subscriptions) {

                    /* Adding the user in the hashmap based on the subscribed
                       genres (represents the keys of the hashmap) */

                    if (subscribers.containsKey(genre)) {
                        ArrayList<User> list = subscribers.get(genre);
                        if (!list.contains(user)) {
                            subscribers.get(genre).add(user);
                        }
                    } else {
                        ArrayList<User> newSubscribers = new ArrayList<>();
                        newSubscribers.add(user);
                        subscribers.put(genre, newSubscribers);
                    }
                }
            }
        }
    }

    /**
     * Notifying all the users
     * @param eventType can be "ADD" or "DELETE"
     * @param movie the movie that was added or deleted
     */

    public void notify (final String eventType, final Movie movie) {
        ArrayList<String> genres = movie.getGenres();
        for (String genre : genres) {
            ArrayList<User> users = subscribers.get(genre);
            if (users != null) {
                for (User user : users) {
                    if (!user.isNotified()) {
                        user.update(new Notification(movie.getName(), eventType));
                        turnOffUserNotifications(user);
                    }
                }
            }
        }
        turnOnUserNotifications();
    }

    /**
     * Deactivate the user notifications (avoiding sending the same notification)
     * @param user the user from the list
     */
    public void turnOffUserNotifications(final User user) {
        user.setNotified(true);
    }
    /**
     * Activate the user notifications for the next actions.
     */
    public void turnOnUserNotifications() {
        for (User user : dataBase.getUsers()) {
            user.setNotified(false);
        }
    }
}
