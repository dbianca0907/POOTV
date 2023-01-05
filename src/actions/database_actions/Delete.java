package actions.database_actions;

import actions.Strategy;
import database.Movie;
import database.User;

import java.util.ArrayList;

public class Delete extends Strategy {

    public int findMovie(final ArrayList<Movie> movies,  String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return 1;
            }
        }
        return -1;
    }

    public void modifyUser(final User currentUser, String accountType) {
        if (accountType.equals("premium")) {
            int numMovies = currentUser.getNumFreePremiumMovies() + 1;
            currentUser.setNumFreePremiumMovies(numMovies);
        } else {
            int numTokens = currentUser.getTokensCount() + 2;
            currentUser.setTokensCount(numTokens);
        }
    }

    public boolean deleteFromList(final ArrayList<Movie> movies, String nameMovie) {
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

// de notificat
    public int execute() {
        String nameMovie = super.getSession().getAction().getDeletedMovie();
        ArrayList<User> users = super.getSession().getDatabase().getUsers();
        ArrayList<Movie> allMovies = super.getSession().getDatabase().getAllMovies();

        if (findMovie(allMovies, nameMovie) == -1)
            return -1;

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

        return 1;
    }
}
