package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.Movie;

import java.util.ArrayList;
import java.util.Map;

public class Rate extends Strategy {
    /**
     * cauta filmul in lista de filme carora li s-au dat rate
     *
     * @return 1, daca nu este deja cumparat
     *        -1, daca a fost cumparat de user deja
     */
    public int findMovie() {
        String nameMovie = super.getSession().getNameCurrMovie();
        ArrayList<Movie> movies = super.getSession().getCurrentUser().getRatedMovies();

        for (Movie movie: movies) {
            if (movie.getName().equals(nameMovie)) {
                return -1;
            }
        }
        return 1;
    }
    /**
     * se calculeaza rate-ul filmelor
     */
    public int execute() {
        double ratingFromInput = super.getSession().getAction().getRate();

        if (ratingFromInput < 1 || ratingFromInput > 5)
            return -1;

        if (super.getSession().getOldFeature().equals("watch")
            || super.getSession().getOldFeature().equals("like")) {
            double rate = super.getSession().getAction().getRate();

            for (Movie movie : super.getSession().getUnbannedMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    /*int numRatings = movie.getNumRatings() + 1;
                    movie.getUserRates().add(rate);
                    movie.setNumRatings(numRatings);
                    int sum = 0;
                    for (Double num : movie.getUserRates()) {
                        sum += num;
                    }
                    double newRate = (double) sum / movie.getUserRates().size();
                    movie.setRating(newRate);
                    super.getSession().setOldFeature("rate");
                    if (findMovie() == -1) {
                        numRatings--;
                        movie.setNumRatings(numRatings);
                        return 0;
                    }
                    return 1;*/
                    String nameUser = super.getSession().getCurrentUser().getCredentials().getName();
                    movie.getUserRates().put(nameUser, rate);
                    int sum = 0;
                    for (Map.Entry<String, Double> entry : movie.getUserRates().entrySet()) {
                        sum += entry.getValue();
                    }
                    movie.setNumRatings(movie.getUserRates().size());
                    movie.setRating((double) sum / movie.getNumRatings());
                    return 1;
                }
            }
        }
        return -1;
    }
}
