package database;

import database.user_data.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Session {
    private boolean isLogged = false;
    private ArrayList<String> backErrors;
    private int actionErr;
    private User currentUser;
    private ArrayList<Movie> startWithMovies;
    private ArrayList<Movie> currentMovieList;
    private ArrayList<Movie> unbannedMovies;
    private String feature;
    private String oldFeature = "nothing";
    private String nameCurrMovie;
    private DataBase database;
    private Queue<String> navigation;
    private Stack<String> history;
    private ActionData action;
    private String pageCurr;


    /**
     * Constructor for the current session
     *
     * @param database the server's Database
     */
    public Session(final DataBase database) {
        this.database = database;
        navigation = new LinkedList<>();
        startWithMovies = new ArrayList<>();
        currentMovieList = new ArrayList<>();
        history = new Stack<>();
        backErrors = new ArrayList<>();
        backErrors.add("login");
        backErrors.add("register");
    }

    /**
     * Getter
     *
     * @return name of the current page
     */
    public String getPageCurr() {
        return pageCurr;
    }

    /**
     * Setter
     *
     * @param pageCurr name of the current page
     */
    public void setPageCurr(final String pageCurr) {
        this.pageCurr = pageCurr;
    }


    /**
     * Create the list of movies that can be watched by the current logged user.
     *
     * @param dataBase the server's Database
     */
    public void selectMovies(final DataBase dataBase) {
        ArrayList<Movie> newCurrList = new ArrayList<Movie>();
        String userCountry = getCurrentUser().getCredentials().getCountry();
        for (Movie movie : dataBase.getAllMovies()) {
            if (!movie.getCountriesBanned().contains(userCountry)) {
                newCurrList.add(movie);
            }
        }
        setCurrentMovieList(newCurrList);
        setUnbannedMovies(newCurrList);
    }

    /**
     * Getter
     *
     * @return list of pages that create an error for a back action
     */
    public ArrayList<String> getBackErrors() {
        return backErrors;
    }

    /**
     * Getter
     *
     * @return the stack that contains all the history of navigation through pages
     */
    public Stack<String> getHistory() {
        return history;
    }

    /**
     * Getter
     *
     * @return -1, 0, 1 depends on the situation, after executing an action
     */
    public int getActionErr() {
        return actionErr;
    }

    /**
     * Setter
     *
     * @param actionErr the type of error after executing an action
     */
    public void setActionErr(int actionErr) {
        this.actionErr = actionErr;
    }

    /**
     * Getter
     *
     * @return the name of the current movie's name (see details)
     */
    public String getNameCurrMovie() {
        return nameCurrMovie;
    }

    /**
     * Setter
     *
     * @param nameCurrMovie the name of the current movie's name, from input (see details)
     */
    public void setNameCurrMovie(final String nameCurrMovie) {
        this.nameCurrMovie = nameCurrMovie;
    }

    /**
     * Getter
     *
     * @return the queue that helps navigate through pages
     */
    public Queue<String> getNavigation() {
        return navigation;
    }

    /**
     * Getter
     *
     * @return the current list of movies
     */
    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    /**
     * Getter
     *
     * @return the list of movies resulted after search action was executed
     */
    public ArrayList<Movie> getStartWithMovies() {
        return startWithMovies;
    }

    /**
     * Getter
     *
     * @return the action from the input
     */
    public ActionData getAction() {
        return action;
    }

    /**
     * Setter
     *
     * @param currentMovieList  the current list of movies
     */
    public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    /**
     * Getter
     *
     * @return the feature of the previous action
     */
    public String getOldFeature() {
        return oldFeature;
    }

    /**
     * Setter
     *
     * @param oldFeature he feature of the previous action
     */
    public void setOldFeature(final String oldFeature) {
        this.oldFeature = oldFeature;
    }

    /**
     * Setter
     *
     * @param action the action from the input
     */
    public void setAction(final ActionData action) {
        this.action = action;
    }

    /**
     * Getter
     *
     * @return the server's Database
     */
    public DataBase getDatabase() {
        return database;
    }

    /**
     * Getter
     *
     * @return the variable that shows if a user is logged or not
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * Getter
     *
     * @return the current action's feature (from input)
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Setter
     *
     * @param feature the current action's feature (from input)
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * Getter
     *
     * @return the logged user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Setter
     *
     * @param currentUser the logged user
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
        selectMovies(getDatabase());
    }

    /**
     * Setter
     *
     * @param logged the variable that shows if a user is logged or not
     */
    public void setLogged(final boolean logged) {
        isLogged = logged;
    }

    /**
     * Getter
     *
     * @return the list of movies that can be watched by the current logged user
     */
    public ArrayList<Movie> getUnbannedMovies() {
        return unbannedMovies;
    }

    /**
     * Setter
     *
     * @param unbannedMovies the list of movies that can be watched by the current logged user
     */
    public void setUnbannedMovies(final ArrayList<Movie> unbannedMovies) {
        this.unbannedMovies = unbannedMovies;
    }
}
