package actions.observer_design;

import database.DataBase;
import database.Movie;
import database.Notification;
import database.User;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
    DataBase dataBase;
    private HashMap<String, ArrayList<User>> subscribers = new HashMap<>();

    public EventManager(DataBase dataBase) {
        this.dataBase = dataBase;
        ArrayList<User> allUsers = dataBase.getUsers();
        for (User user : allUsers) {
            ArrayList<String> subscriptions = user.getSubscribedGenres();
            if (subscriptions != null) {
                for (String genre : subscriptions) {
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

    public void notify (String eventType, Movie movie) {

        ArrayList<String> genres = movie.getGenres();
        for (String genre : genres) {
            ArrayList<User> users = subscribers.get(genre);
            if (users != null) {
                for (User user : users) {
                    if (!user.isNotified()) {
                        user.update(new Notification(movie.getName(), eventType));
                    }
                }
            }
        }
        turnOnUserNotifs();
    }

    public void turnOnUserNotifs() {
        for (User user : dataBase.getUsers()) {
            user.setNotified(false);
        }
    }
}
