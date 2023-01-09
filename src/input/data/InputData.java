package input.data;

import input.actions.ActionsInput;

import java.util.ArrayList;

public final class InputData {
    private ArrayList<InputUser> users;
    private ArrayList<InputMovie> movies;
    private ArrayList<ActionsInput> actions;

    /**
     * Getter
     *
     * @return list of all users
     */
    public ArrayList<InputUser> getUsers() {
        return users;
    }

    /**
     * Getter
     *
     * @return list of all movies
     */
    public ArrayList<InputMovie> getMovies() {
        return movies;
    }

    /**
     * Getter
     *
     * @return list of all actions from input
     */
    public ArrayList<ActionsInput> getActions() {
        return actions;
    }
}
