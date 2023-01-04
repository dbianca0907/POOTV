package actions;

import database.Movie;
public class RateAction extends Strategy {
    /**
     * se calculeaza rate-ul filmelor
     */
    public int execute() {
        double ratingFromInput = super.getSession().getAction().getRate();

        if (ratingFromInput < 1 || ratingFromInput > 5)
            return -1;

        if (super.getSession().getOldFeature().equals("watch")
                && super.getSession().getActionErr() == 1) {
            double rate = super.getSession().getAction().getRate();

            for (Movie movie : super.getSession().getDatabase().getAllMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    movie.getUserRates().add(rate);
                    movie.setNumRatings(movie.getUserRates().size());
                    int sum = 0;
                    for (Double num : movie.getUserRates()) {
                        sum += num;
                    }
                    double newRate = (double) sum / movie.getNumRatings();
                    movie.setRating(newRate);
                    super.getSession().setOldFeature("rate");
                    return 1;
                }
            }
        }
        return -1;
    }
}
