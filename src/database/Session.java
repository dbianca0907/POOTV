package database;

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
     * getter
     * @return
     */
    public String getPageCurr() {
        return pageCurr;
    }

    /**
     * setez pagina curenta
     * @param pageCurr
     */
    public void setPageCurr(final String pageCurr) {
        this.pageCurr = pageCurr;
    }

    /**
     * constructor pentru sesiunea curenta
     * @param database
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
     * functie care seteaza lista curenta de filme, in functie de tara de origine
     * userului si tara in care filmul este banat
     * @param dataBase
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

    public ArrayList<String> getBackErrors() {
        return backErrors;
    }

    public Stack<String> getHistory() {
        return history;
    }

    public void setHistory(Stack<String> history) {
        this.history = history;
    }

    public int getActionErr() {
        return actionErr;
    }

    public void setActionErr(int actionErr) {
        this.actionErr = actionErr;
    }

    /**
     * getter
     * @return
     */
    public String getNameCurrMovie() {
        return nameCurrMovie;
    }

    /**
     * setter
     * @param nameCurrMovie
     */
    public void setNameCurrMovie(final String nameCurrMovie) {
        this.nameCurrMovie = nameCurrMovie;
    }
    /**
     * getter
     * @return
     */
    public Queue<String> getNavigation() {
        return navigation;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<Movie> getStartWithMovies() {
        return startWithMovies;
    }
    /**
     * getter
     * @return
     */
    public ActionData getAction() {
        return action;
    }

    /**
     * setter
     * @param currentMovieList
     */
    public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public String getOldFeature() {
        return oldFeature;
    }

    public void setOldFeature(String oldFeature) {
        this.oldFeature = oldFeature;
    }

    /**
     * setter
     * @param action
     */
    public void setAction(final ActionData action) {
        this.action = action;
    }
    /**
     * getter
     * @return
     */
    public DataBase getDatabase() {
        return database;
    }
    /**
     * getter
     * @return
     */
    public boolean isLogged() {
        return isLogged;
    }
    /**
     * getter
     * @return
     */
    public String getFeature() {
        return feature;
    }

    /**
     * setter
     * @param feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }
    /**
     * getter
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * setter
     * @param currentUser
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
        selectMovies(getDatabase());
    }

    /**
     * setter
     * @param logged
     */
    public void setLogged(final boolean logged) {
        isLogged = logged;
    }

    public ArrayList<Movie> getUnbannedMovies() {
        return unbannedMovies;
    }

    public void setUnbannedMovies(ArrayList<Movie> unbannedMovies) {
        this.unbannedMovies = unbannedMovies;
    }
}
