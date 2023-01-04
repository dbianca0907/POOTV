package database;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private HashMap<String, User> userHashMap;
    private ArrayList<User> users;
    private ArrayList<Movie> allMovies;

    /**
     * constructor
     * @param users
     * @param allMovies
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
     * functie de adaugat userii in hashmap
     * @param newUser
     */
    public void addUsers(final User newUser) {
            String key = newUser.getCredentials().getName()
                        + newUser.getCredentials().getPassword();
            getUserHashMap().put(key, newUser);
    }

    /**
     * getter
     * @return
     */
    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }

    /**
     * setter
     * @param users
     */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }
}
