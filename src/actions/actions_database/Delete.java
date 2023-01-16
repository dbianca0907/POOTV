package actions.actions_database;

import actions.observer_design.EventManager;
import actions.strategy_design.Strategy;
import database.movie_data.Movie;
import database.user_data.User;

import java.util.ArrayList;

public class Delete extends Strategy {
    /**
     *  Search in the given list a movie, based on its name.
     *
     * @param movies the given list of movies, in this case the list
     *               with all the movies from Database
     * @param nameMovie the name of the movie received from input
     * @return the found movie, if it is in the Database
     *         null, otherwise
     */

    public Movie findMovie(final ArrayList<Movie> movies, final  String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Modifying the number of free movies or tokens, in case of deleting a purchased movie.
     *
     * @param currentUser the user that is currently logged
     */
    public void modifyUser(final User currentUser) {
        String accountType = currentUser.getCredentials().getAccountType();
        if (accountType.equals("premium")) {
            int numMovies = currentUser.getNumFreePremiumMovies() + 1;
            currentUser.setNumFreePremiumMovies(numMovies);
        } else {
            int numTokens = currentUser.getTokensCount() + 2;
            currentUser.setTokensCount(numTokens);
        }
    }

    /**
     * Deleting the movie if it is found in the user movie list.
     *
     * @param movies user movie list
     * @param nameMovie the name of the movie that has to be deleted
     * @return true, if there was found a movie and was deleted
     *         false, otherwise
     */
    public boolean deleteFromList(final ArrayList<Movie> movies, final String nameMovie) {
        if (movies != null) {
            Movie movie = findMovie(movies, nameMovie);
            if (movie != null) {
                movies.remove(movie);
                return true;
            }
        }
        return false;
    }

    /**
     * Deleting the movie given from input and notifying the users.
     *
     * @return -1, if the movie is already in the Database
     *          1, if the action was completed successfully
     */
    @Override
    public int execute() {
        EventManager eventManager = new EventManager(super.getSession().getDatabase());
        String nameMovie = super.getSession().getAction().getDeletedMovie();
        ArrayList<User> users = super.getSession().getDatabase().getUsers();
        ArrayList<Movie> allMovies = super.getSession().getDatabase().getAllMovies();
        Movie movie = findMovie(allMovies, nameMovie);

        if (movie == null) {
            return -1;
        }

        deleteFromList(allMovies, nameMovie);
        for (User user : users) {
            if (deleteFromList(user.getPurchasedMovies(), nameMovie)) {
                modifyUser(user);
            }
            deleteFromList(user.getWatchedMovies(), nameMovie);
            deleteFromList(user.getLikedMovies(), nameMovie);
            deleteFromList(user.getRatedMovies(), nameMovie);
        }
        eventManager.notify("DELETE", movie);
        return 1;
    }
}
