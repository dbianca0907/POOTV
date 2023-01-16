package output;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.movie_data.Movie;
import database.session_data.Session;
import database.user_data.Credentials;
import database.user_data.Notification;
import database.user_data.User;

import java.util.ArrayList;

public final class Printer {
    private static Printer instance = null;

    private Printer() {
    }

    /**
     * Singleton implementation.
     */
    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    /**
     * Printing message for a "change page" error.
     */

    public void printBasicErrorPage(final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        String error = "Error";
        node.put("error", error);
        node.set("currentMoviesList", JsonNodeFactory.instance.arrayNode());
        node.set("currentUser", (JsonNode) null);
        output.add(node);
    }

    /**
     * Printing the error message for the other errors than the one above.
     *
     * @param session the current user session
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
     * Printing all the information about the current page.
     *
     * @param session the user current session
     */
    public void printOnPage(final Session session, final ArrayNode output) {

        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayList<Movie> curr = null;
        if (session.getFeature().equals("search")) {
            curr = session.getStartWithMovies();
        } else if (session.getPageCurr().equals("movies")) {
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
     * Printing the recommendation.
     *
     * @param session the user current session
     */
    public void printRecommendation(final Session session, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.set("error", (JsonNode) null);
        node.set("currentMoviesList", (JsonNode) null);
        node.put("currentUser", printCurrUser(session.getCurrentUser()));
        output.add(node);
    }

    /**
     * Printing the special message for "see details" page.
     *
     * @param session the user current session
     * @param movie   movie from input
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
     * Printing the information about user.
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
        node.set("notifications", printNotification(user.getNotifications()));
        return node;
    }

    /**
     * Printing the movies from a specific array of movies.
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
     * Printing the message for notifications.
     */
    public ArrayNode printNotification(final ArrayList<Notification> notifications) {
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        if (notifications == null) {
            return array;
        }
        for (Notification notif : notifications) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("movieName", notif.getNameMovie());
            node.put("message", notif.getMessage());
            array.add(node);
        }
        return array;
    }

    /**
     * Printing the information about a movie.
     */
    public ObjectNode printMovie(final Movie movie) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        String year = String.valueOf(movie.getYear());

        node.put("name", movie.getName());
        node.put("year", year);
        node.put("duration", movie.getDuration());
        node.set("genres", makeArrString(movie.getGenres()));
        node.set("actors", makeArrString(movie.getActors()));
        node.set("countriesBanned", makeArrString(movie.getCountriesBanned()));
        node.put("numLikes", movie.getNumLikes());
        node.put("rating", movie.getRating());
        node.put("numRatings", movie.getNumRatings());
        return node;
    }

    /**
     * Printing the credentials about an user
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
     * Making an Array Node from an list
     */
    public ArrayNode makeArrString(final ArrayList<String> arr) {
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        for (String str : arr) {
            array.add(str);
        }
        return array;
    }
}
