package input;

import database.action_data.ActionData;
import database.user_data.Credentials;
import database.movie_data.Movie;
import database.user_data.User;
import input.actions.ActionsInput;
import input.data.InputCredentials;
import input.data.InputData;
import input.data.InputMovie;
import input.data.InputUser;

import java.util.ArrayList;

public class Parsing {
    /**
     * Parsing all the action's information from input to "ActionData" class.
     *
     * @param input information from input
     * @return reference to the new created object
     */
    public static ActionData parseAction(final ActionsInput input) {
        ActionData actionData = new ActionData();
        actionData.setType(input.getType());
        if (input.getType().equals("change page")) {
            actionData.setPage(input.getPage());
            if (input.getPage().equals("see details")) {
                actionData.setMovie(input.getMovie());
            }
        } else if (input.getType().equals("on page")) {
            actionData.setFeature(input.getFeature());
            if (input.getFeature().equals("register")) {
                actionData.setCredentials(new Credentials(input.getCredentials().getName(),
                        input.getCredentials().getPassword(),
                        input.getCredentials().getAccountType(),
                        input.getCredentials().getCountry(),
                        input.getCredentials().getBalance()));
            }
            if (input.getFeature().equals("login")) {
                actionData.setCredentials(new Credentials(input.getCredentials().getName(),
                        input.getCredentials().getPassword(), null,
                        null, 0));
            }
            if (input.getFilters() != null) {
                if (input.getFilters().getContains() != null) {
                    if (input.getFilters().getContains().getActors() != null) {
                        actionData.setActors(new ArrayList<>(
                                input.getFilters().getContains().getActors()));
                    }
                    if (input.getFilters().getContains().getGenre() != null) {
                        actionData.setGenre(new ArrayList<>(
                                input.getFilters().getContains().getGenre()));
                    }
                }
                if (input.getFilters().getSort() != null) {
                    if (input.getFilters().getSort().getRating() != null) {
                        actionData.setRating(input.getFilters().getSort().getRating());
                    }
                    if (input.getFilters().getSort().getDuration() != null) {
                        actionData.setDuration(input.getFilters().getSort().getDuration());
                    }
                }
            }
            if (input.getFeature().equals("search")) {
                actionData.setStartsWith(input.getStartsWith());
            }
            if (input.getFeature().equals("buy tokens")) {
                actionData.setCount(input.getCount());
            }
            if (input.getFeature().equals("rate")) {
                actionData.setRate(input.getRate());
            }
            if (input.getFeature().equals("subscribe")) {
                actionData.setSubscribedGenre(input.getSubscribedGenre());
            }
        } else if (input.getType().equals("database")) {
            if (input.getFeature().equals("add")) {
                actionData.setAddedMovie(parseMovie(input));
            } else {
                actionData.setDeletedMovie(input.getDeletedMovie());
            }
        }
        return actionData;
    }

    /**
     * Parsing all the information about users from input in other class.
     *
     * @param input information from input
     * @return reference to the new created object
     */
    public static ArrayList<User> parseUsers(final InputData input) {
        ArrayList<InputUser> usersInput = input.getUsers();
        ArrayList<User> users = new ArrayList<>();

        for (InputUser userInput : usersInput) {
            InputCredentials cr = userInput.getCredentials();
            Credentials userCr = new Credentials(cr.getName(), cr.getPassword(),
                    cr.getAccountType(), cr.getCountry(), cr.getBalance());
            User user = new User(userCr);
            users.add(user);
        }
        return users;
    }

    /**
     * Parsing all the movies in the Database from input.
     *
     * @param input information from input
     * @return reference to the new created object
     */
    public static ArrayList<Movie> parseMovies(final InputData input) {
        ArrayList<InputMovie> moviesInput = input.getMovies();
        ArrayList<Movie> movies = new ArrayList<>();

        for (InputMovie movieInput : moviesInput) {
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(),
                    movieInput.getDuration(), movieInput.getGenres(),
                    movieInput.getActors(), movieInput.getCountriesBanned());
            movies.add(movie);
        }
        return movies;
    }

    /**
     * Parsing all the information about a movie int the Database.
     *
     * @param input information from input
     * @return reference to the new created object
     */
    public static Movie parseMovie(final ActionsInput input) {
        Movie movie = new Movie(input.getAddedMovie().getName(), input.getAddedMovie().getYear(),
                input.getAddedMovie().getDuration(), input.getAddedMovie().getGenres(),
                input.getAddedMovie().getActors(), input.getAddedMovie().getCountriesBanned());
        return movie;
    }
}
