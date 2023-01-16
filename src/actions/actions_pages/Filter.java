package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.movie_data.Movie;

import java.util.ArrayList;
import java.util.Collections;

public class Filter extends Strategy {

    /**
     * Sorting the movies based on movie's duration.
     *
     * @param movies the current list of movies
     * @param order  increasing or decreasing order
     * @return the sorted list of movies
     */
    public ArrayList<Movie> sortByDuration(final ArrayList<Movie> movies, final String order) {
        ArrayList<Movie> sortedMovies = new ArrayList<>();
        ArrayList<Integer> duration = new ArrayList<>();
        ArrayList<Movie> copy = new ArrayList<>(movies);

        for (int i = 0; i < movies.size(); i++) {
            duration.add(movies.get(i).getDuration());
        }
        if (order.equals("increasing")) {
            Collections.sort(duration);
        } else {
            Collections.sort(duration, Collections.reverseOrder());
        }
        for (int i = 0; i < duration.size(); i++) {
            for (Movie movie : copy) {
                if (movie.getDuration() == duration.get(i)) {
                    sortedMovies.add(movie);
                    copy.remove(movie);
                    break;
                }
            }
        }

        return sortedMovies;
    }

    /**
     * Sorting the movies based on movie's rating.
     *
     * @param movies the current list of movies
     * @param order  increasing or decreasing order
     * @return the sorted list of movies
     */
    public ArrayList<Movie> sortByRate(final ArrayList<Movie> movies, final String order) {
        ArrayList<Movie> sortedMovies = new ArrayList<>();
        ArrayList<Double> rate = new ArrayList<>();
        ArrayList<Movie> copy = new ArrayList<>(movies);

        for (int i = 0; i < movies.size(); i++) {
            rate.add(movies.get(i).getRating());
        }
        if (order.equals("increasing")) {
            Collections.sort(rate);
        } else {
            Collections.sort(rate, Collections.reverseOrder());
        }
        for (int i = 0; i < rate.size(); i++) {
            for (Movie movie : copy) {
                if (movie.getRating() == rate.get(i)) {
                    sortedMovies.add(movie);
                    copy.remove(movie);
                    break;
                }
            }
        }

        return sortedMovies;
    }

    /**
     * Applying both filters on the list.
     *
     * @param movies the list of available movies for the current user
     * @param actors the list of actors from input
     * @param genres the list of genres from input
     * @return the modified list of movies
     */

    public ArrayList<Movie> containsAll(final ArrayList<Movie> movies,
                                        final ArrayList<String> actors,
                                        final ArrayList<String> genres) {
        boolean contains;
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            contains = true;
            for (String actor : actors) {
                if (!movie.getActors().contains(actor)) {
                    contains = false;
                    break;
                }
            }
            for (String genre : genres) {
                if (!movie.getGenres().contains(genre)) {
                    contains = false;
                    break;
                }
            }
            if (contains) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    /**
     * Applying one filter on the list.
     *
     * @param movies   the list of available movies for the current user
     * @param atribute the type of filter
     * @param criteria the list of actors or genres from input
     * @return the modified list of movies
     */
    public ArrayList<Movie> containsOneOfThem(final ArrayList<Movie> movies, final String atribute,
                                              final ArrayList<String> criteria) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            int numCr = 0;
            for (String element : criteria) {
                if (atribute.equals("actors")
                        && movie.getActors().contains(element)) {
                    numCr++;
                }
                if (atribute.equals("genre")
                        && movie.getGenres().contains(element)) {
                    numCr++;
                }
            }
            if (numCr == criteria.size()) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    /**
     * Applying filters on the movie lists
     *
     * @return 1, there are no errors to occur
     */
    @Override
    public int execute() {

        if (getSession().getAction().getActors() != null
                && getSession().getAction().getGenre() != null) {
            getSession().setCurrentMovieList(containsAll(getSession().getUnbannedMovies(),
                    getSession().getAction().getActors(),
                    getSession().getAction().getGenre()));
        } else {
            if (getSession().getAction().getActors() != null) {
                getSession().setCurrentMovieList(containsOneOfThem(getSession().getUnbannedMovies(),
                        "actors", getSession().getAction().getActors()));
            }
            if (getSession().getAction().getGenre() != null) {
                getSession().setCurrentMovieList(containsOneOfThem(getSession().getUnbannedMovies(),
                        "genre", getSession().getAction().getGenre()));
            }
        }
        if (getSession().getAction().getRating() != null) {
            getSession().setCurrentMovieList(sortByRate(getSession().getCurrentMovieList(),
                    getSession().getAction().getRating()));
        }
        if (getSession().getAction().getDuration() != null) {
            getSession().setCurrentMovieList(sortByDuration(getSession().getCurrentMovieList(),
                    getSession().getAction().getDuration()));
        }
        return 1;
    }

}
