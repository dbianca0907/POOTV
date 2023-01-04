package output;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Movie;
import database.Session;
import database.User;
import database.Credentials;

import java.util.ArrayList;
public final class Printer {
    private static Printer instance = null;

    private Printer() { }

    /**
     * implementare singleton lA PRINTER
     * @return
     */
    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    /**
     * afisare eroare standard, in care toatre campurile sunt null
     * @param session
     * @param output
     */
    public void printBasicErrorPage(final Session session, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        String error = "Error";
        node.put("error", error);
        node.set("currentMoviesList", JsonNodeFactory.instance.arrayNode());
        node.set("currentUser", (JsonNode) null);
        output.add(node);
    }

    /**
     * printez eroare, pentru cazurile in care campurile trebuie populate
     * @param session
     * @param output
     */
    public void printBasicError(final Session session, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayList<Movie> curr;
        if (session.getCurrentMovieList().isEmpty()) {
            curr = null;
        } else {
            curr = session.getCurrentMovieList();
        }

        if (session.getFeature().equals("search")) {
            node.put("error", (JsonNode) null);
            curr = null;
        } else {
            String error = "Error";
            node.put("error", error);
        }
        if (session.getPageCurr().equals("movies")
                || session.getPageCurr().equals("see details")) {
            node.set("currentMoviesList", printMovies(curr));
        } else {
            node.set("currentMoviesList", JsonNodeFactory.instance.arrayNode());
        }
        if (session.isLogged()) {
            node.set("currentUser", printCurrUser(session.getCurrentUser()));
        } else {
            node.set("currentUser", (JsonNode) null);
        }

        output.add(node);
    }

    /**
     * metoda care se ocupa de afisarea pa un page,
     * sau dupa ce anumite actiuni au fost finalizate cu succes
     * @param session
     * @param output
     */
    public void printOnPage(final Session session, final ArrayNode output) {

        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayList<Movie> curr = null;
        if (session.getFeature().equals("search")) {
            curr = session.getStartWithMovies();
        } else if (session.getPageCurr().equals("movies")) {
            //System.out.println("afiseaza filmele filtrate");
            curr = session.getCurrentMovieList();
        }
        node.set("error", (JsonNode) null);
        node.set("currentMoviesList", printMovies(curr));

        if (session.isLogged()) {
            node.put("currentUser", printCurrUser(session.getCurrentUser()));
        } else {
            node.set("currentUser", (JsonNode) null);
        }

        output.add(node);
    }

    /**
     * metoda speciala pentru printul de la pagina "see details"
     * @param session
     * @param output
     * @param movie
     */
    public void printSeeDetails(final Session session, final ArrayNode output, Movie movie) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode arrayMovie = JsonNodeFactory.instance.arrayNode();
        arrayMovie.add(printMovie(movie));

        node.set("error", (JsonNode) null);
        node.put("currentMoviesList", arrayMovie);
        node.put("currentUser", printCurrUser(session.getCurrentUser()));
        output.add(node);
    }

    /**
     * metoda care creeaza nodul specific pentru afisarea detaliilor unui user
     * @param user
     * @return
     */
    public ObjectNode printCurrUser(final User user) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("credentials", printCredentials(user.getCredentials()));
        node.put("tokensCount", user.getTokensCount());
        node.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        node.set("purchasedMovies", printMovies(user.getPurchasedMovies()));
        node.set("watchedMovies", printMovies(user.getWatchedMovies()));
        node.set("likedMovies", printMovies(user.getLikedMovies()));
        node.set("ratedMovies", printMovies(user.getRatedMovies()));

        return node;
    }

    /**
     * metoda specifica pentru printarea filmelor dintr-o lista
     * @param movies
     * @return
     */
    public ArrayNode printMovies(final ArrayList<Movie> movies) {
        if (movies == null) {
            return JsonNodeFactory.instance.arrayNode();
        }
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        for (Movie movie : movies) {
            array.add(printMovie(movie));
        }
        return array;
    }

    /**
     * metoda care creeaza nodul specific afisarii detaliilor unui film
     * @param movie
     * @return
     */
    public ObjectNode printMovie(final Movie movie) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        String str = String.format("%.2f", movie.getRating());
        double rate = Double.parseDouble(str);

        node.put("name", movie.getName());
        node.put("year", movie.getYear());
        node.put("duration", movie.getDuration());
        node.set("genres", makeArrString(movie.getGenres()));
        node.set("actors", makeArrString(movie.getActors()));
        node.set("countriesBanned", makeArrString(movie.getCountriesBanned()));
        node.put("numLikes", movie.getNumLikes());
        node.put("rating", rate);
        node.put("numRatings", movie.getNumRatings());
        return node;
    }

    /**
     * metoda care creeaza nodul specific printului detaliilor userului
     * @param credentials
     * @return
     */
    public ObjectNode printCredentials(final Credentials credentials) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("name", credentials.getName());
        node.put("password", credentials.getPassword());
        node.put("accountType", credentials.getAccountType());
        node.put("country", credentials.getCountry());
        node.put("balance", Integer.toString(credentials.getBalance()));

        return node;
    }

    /**
     * metoda care creeaza un arrayNode dintr-un array de stringuri
     * @param arr
     * @return
     */
    public ArrayNode makeArrString(final ArrayList<String> arr) {
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        for (String str : arr) {
            array.add(str);
        }
        return array;
    }
}
