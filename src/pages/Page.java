package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.movie_data.Movie;
import database.session_data.Session;
import output.Printer;
import pages.hierarchy_of_pages.HomepageLogged;
import pages.hierarchy_of_pages.HomepageUnlogged;

import java.util.ArrayList;

public class Page {
    private ArrayNode output;
    private Session session;

    /**
     * Constructor
     */
    public Page(final ArrayNode output, final Session session) {
        this.output = output;
        this.session = session;
    }


    /**
     * Verify if the action from input can be executed on the current page.
     *
     * @param nameAction the action's name from the input
     * @return 1, if the action is correct
     * -1, otherwise
     */
    public int verifyAction(final String nameAction) {
        if (!getSession().getFeature().equals(nameAction)) {
            Printer p = Printer.getInstance();
            p.printBasicErrorPage(getOutput());
            return -1;
        }
        return 1;
    }

    /**
     * Finding the movie from the list by its name.
     *
     * @param movies the list of movies
     * @param name   the name of the wanted movie
     * @return the movie, if it is found
     * null, otherwise
     */
    public Movie findMovieInData(final ArrayList<Movie> movies, final String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Printing the details about a movie
     */
    public void printOneMovie(final Movie movie) {
        Printer p = Printer.getInstance();
        p.printSeeDetails(getSession(), getOutput(), movie);
    }

    /**
     * Printing a type of error's message.
     */
    public void printBasicErrorPage() {
        Printer p = Printer.getInstance();
        p.printBasicErrorPage(getOutput());
    }

    /**
     * Printing all the details about a current page
     */
    public void printOnPage() {
        Printer p = Printer.getInstance();
        p.printOnPage(getSession(), output);
    }

    /**
     * Printing a type of error's message.
     */
    public void printBasicError() {
        Printer p = Printer.getInstance();
        p.printBasicError(getSession(), getOutput());
    }

    /**
     * Execute the specific action for this page.
     * "Page" doesn't have a specific action.
     */
    public void actions() {
        printBasicError();
    }

    /**
     * Navigate between pages
     */
    public void move() {
        if (getSession().getNavigation().peek().equals("logout")) {
            getSession().getNavigation().remove();
            HomepageUnlogged page = new HomepageUnlogged(getOutput(), getSession());
            page.move();
        } else if (getSession().getPageCurr().equals("logged")) {
            HomepageLogged page = new HomepageLogged(getOutput(), getSession());
            page.move();
        }
    }

    /**
     * Adding the current page name in the history stack
     */
    public void addToHistory(final String namePage) {
        if (!getSession().getHistory().isEmpty()) {
            if (!getSession().getHistory().peek().equals(namePage)) {
                getSession().getHistory().push(namePage);
            }
        } else {
            getSession().getHistory().push(namePage);
        }
    }

    /**
     * Navigate through pages.
     *
     * @param namePage next page name
     */
    public void navigate(final String namePage) {
        PageFactory factory = new PageFactory();

        // remove from the navigation queue page name
        getSession().getNavigation().remove();

        // getting the nest page, using factory design pattern, based on name
        Page page = factory.getPage(namePage, output, getSession());

        // moving forward into navigation
        page.move();
    }

    /**
     * getter
     */
    public Session getSession() {
        return session;
    }

    /**
     * getter
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * setter
     */
    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    /**
     * setter
     */
    public void setSession(final Session session) {
        this.session = session;
    }
}
