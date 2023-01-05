package actions.pages_actions;

import actions.Strategy;
import database.Movie;

import java.util.ArrayList;
import java.util.Collections;

public class Filter extends Strategy {

    /**
     * functie care seteaza filmele, prima oara in functie de Duration
     * apoi dupa rating
     * @param movies lista curenta de filme
     * @param order ordinea in care vrem sa fie sortata lista
     * @return lista curenta de filme, pentru cazul in care valorile erau egale,
     *       sau lista sortata dupa rating
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
     * functie care sorteaza in functie de numarul de rating-uri
     * @param movies liste de filme curenta
     * @param order ordinea in care vrem sa se realizeze sortarea
     * @return lista curenta de filme, pentru cazul in care valorile erau egale,
     *          sau lista sortata dupa rating
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
     * metoda pentru a pastra filmele care contin actorii si tipul filmului din input
     * se trateaza cazul in care se doreste filtrarea filmelor dupa ambele criterii
     * @param movies lista curenta de filme
     * @param actors lista de actori din input
     * @param genres lista de categorii ale filmului din input
     * @return lista de filme filtrata
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
     * metoda pentru a trata cazul de filtrare in functie de criterii,
     * daca primim doar unul dintre array-uri la input
     * @param movies lista curenta de filme
     * @param atribute criteriul dupa care dorim filtrarea (actors/genre)
     * @param criteria arrayList-ul primit de lainput(actors/genres)
     * @return lista de filme filtrata
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
                    && movie.getGenres().contains(element) ) {
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
     * functia care filtreaza lista curenta a userului logat din session
     */
    public int execute() {
        if (super.getSession().getAction().getActors() != null
                && super.getSession().getAction().getGenre() != null) {
            super.getSession().setCurrentMovieList(containsAll(super.getSession().getUnbannedMovies(),
                    super.getSession().getAction().getActors(),
                    super.getSession().getAction().getGenre()));
        } else {
            if (super.getSession().getAction().getActors() != null) {
                super.getSession().setCurrentMovieList(containsOneOfThem(super.getSession().getUnbannedMovies(),
                        "actors", super.getSession().getAction().getActors()));
            }
            if (super.getSession().getAction().getGenre() != null) {
                super.getSession().setCurrentMovieList(containsOneOfThem(super.getSession().getUnbannedMovies(),
                        "genre", super.getSession().getAction().getGenre()));
            }
        }
        if (super.getSession().getAction().getRating() != null) {
            super.getSession().setCurrentMovieList(sortByRate(super.getSession().getCurrentMovieList(),
                    super.getSession().getAction().getRating()));
        }
        if (super.getSession().getAction().getDuration() != null) {
            super.getSession().setCurrentMovieList(sortByDuration(super.getSession().getCurrentMovieList(),
                    super.getSession().getAction().getDuration()));
        }
        return 1;
    }

}
