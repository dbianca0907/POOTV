package database;

import database.user_data.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private HashMap<String, User> userHashMap;
    private ArrayList<User> users;
    private ArrayList<Movie> allMovies;

    /**
     * Constructor for the server's Database.
     *
     * @param users     all users from input
     * @param allMovies all movies from input
     */
    public DataBase(final ArrayList<User> users,
                    final ArrayList<Movie> allMovies) {
        this.users = users;
        this.allMovies = allMovies;
        this.userHashMap = new HashMap<>();
        for (User user : users)
            addUsers(user);
    }

    /**
     * Adding all the users in a hashmap
     *
     * @param newUser new user from input
     */
    public void addUsers(final User newUser) {
        String key = newUser.getCredentials().getName()
                + newUser.getCredentials().getPassword();
        getUserHashMap().put(key, newUser);
    }

    /**
     * Getter
     *
     * @return the hashmap of users
     */
    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    /**
     * Getter
     *
     * @return the list of all  users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getter
     *
     * @return the list of all movies
     */
    public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }
}
