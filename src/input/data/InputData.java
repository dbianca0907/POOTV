package input.data;
import input.actions.ActionsInput;

import java.util.ArrayList;

public final class InputData {
    private ArrayList<InputUser> users;
    private ArrayList<InputMovie>  movies;
    private ArrayList<ActionsInput> actions;

    /**
     * getter
     * @return
     */
    public ArrayList<InputUser> getUsers() {
        return users;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<InputMovie> getMovies() {
        return movies;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    /**
     * setter
     * @param users
     */
    public  void setUsers(final ArrayList<InputUser> users) {
        this.users = users;
    }

    /**
     * setter
     * @param movies
     */
    public void setMovies(final ArrayList<InputMovie> movies) {
        this.movies = movies;
    }

    /**
     * setter
     * @param actions
     */
    public  void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }
}
