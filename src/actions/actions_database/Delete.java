package actions.actions_database;

import actions.observer_design.EventManager;
import actions.strategy_design.Strategy;
import database.Movie;
import database.User;

import java.util.ArrayList;

public class Delete extends Strategy {

    public Movie findMovie(final ArrayList<Movie> movies, final  String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return movie;
            }
        }
        return null;
    }

    public void modifyUser(final User currentUser, final String accountType) {
        if (accountType.equals("premium")) {
            int numMovies = currentUser.getNumFreePremiumMovies() + 1;
            currentUser.setNumFreePremiumMovies(numMovies);
        } else {
            int numTokens = currentUser.getTokensCount() + 2;
            currentUser.setTokensCount(numTokens);
        }
    }

    public boolean deleteFromList(final ArrayList<Movie> movies, final String nameMovie) {
        boolean find = false;
        if (movies != null) {
            ArrayList<Movie> toRemove = new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.getName().equals(nameMovie)) {
                    toRemove.add(movie);
                    find = true;
                }
            }
            movies.removeAll(toRemove);
        }
        return find;
    }

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
                String accountType = user.getCredentials().getAccountType();
                modifyUser(user, accountType);
            }
            deleteFromList(user.getWatchedMovies(), nameMovie);
            deleteFromList(user.getLikedMovies(), nameMovie);
            deleteFromList(user.getRatedMovies(), nameMovie);
        }
        eventManager.notify("DELETE", movie);
        return 1;
    }
}
