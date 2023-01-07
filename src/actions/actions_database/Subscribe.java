package actions.actions_database;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;

public class Subscribe extends Strategy {


    public int execute() {
        String genre = super.getSession().getAction().getSubscribedGenre();
        ArrayList<Movie> movies = super.getSession().getUnbannedMovies();
        ArrayList<String> userSubscriptions = super.getSession().getCurrentUser().getSubscribedGenres();

        if (userSubscriptions.contains(genre)
            || movies ==null) {
            System.out.println("a returnat in primul if eroare in sub");
            return -1;
        }

        for (Movie movie : movies) {
            if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                if (movie.getGenres().contains(genre)) {
                    getSession().getCurrentUser().getSubscribedGenres().add(genre);
                    return 1;
                }
            }
        }
        System.out.println("a returnat in al doilea if eroare in sub");

        return -1;
    }
}
