package pages;

import backup_actions.SnapshotAction;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Movie;
import database.Session;
import output.Printer;

import java.util.ArrayList;

public class Page {
    private ArrayNode output;
    private Session session;

    /**
     * constructor
     * @param output arrayNode-ul primit fin Main
     * @param session sesiunea curenta a userului
     */
    public Page(final ArrayNode output, final Session session) {
        this.output = output;
        this.session = session;
    }

    public SnapshotAction createSnapshot() {
        return new SnapshotAction(getSession());
    }

    /**
     * verifica daca actiunea precizata in input este cea specifica paginii
     * @param nameAction numele actiunii care ar trebui executata
     * @return -1, daca actiunea este incorecta
     *          1, altfel
     */
    public int verifAction(final String nameAction) {
        if (!getSession().getFeature().equals(nameAction)) {
            Printer p = Printer.getInstance();
            p.printBasicErrorPage(getSession(), getOutput());
            return -1;
        }
        return 1;
    }

    /**
     * functie care gaseste filmul primit in currentList-ul de filme
     * @param movies lista totala de filme
     * @param name numele filmului care trebuie sa fie cautat
     * @return filmul, daca este gasit
     *           null, altfel
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
     * print-ul specific pentru afisarea detaliilor unui singur film
     * @param movie filmul ale carui detalii trebuie afisate
     */
    public void printOneMovie(final Movie movie) {
        Printer p = Printer.getInstance();
        p.printSeeDetails(getSession(), getOutput(), movie);
    }

    /**
     * se apeleaza din printer eroarea in care nu toate campurile trebuie sa fie null
     */
    public void printBasicErrorPage() {
        Printer p = Printer.getInstance();
        p.printBasicErrorPage(getSession(), getOutput());
    }

    /**
     * se apeleaza din printer metoda specifica pentru afisarea detaliilor paginii curente
     */
    public void printOnPage() {
        Printer p = Printer.getInstance();
        p.printOnPage(getSession(), output);
    }

    /**
     * se apeleaza eroarea basic din printer in care tpoate campurile sunt null
     */
    public void printBasicError() {
        Printer p = Printer.getInstance();
        p.printBasicError(getSession(), getOutput());
    }
    /**
     * metoda comuna a paginilor
     */
    public void actions() { }

    /**
     * se incepe iterarea prin pagini, la inceput in platform este adaugata in coada numele
     * paginii Home Neautentificat, iar currPage este o instanta a clasei Page, practic mutarea prin
     * paginii incepe de aici
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
    public void addToHistory(String namePage) {
        if (!getSession().getHistory().isEmpty()) {
            if (!getSession().getHistory().peek().equals(namePage)) {
                getSession().getHistory().push(namePage);
                System.out.println(getSession().getHistory().peek());
            }
        } else {
            getSession().getHistory().push(namePage);
            System.out.println(getSession().getHistory().peek());
        }
    }

    /**
     *
     * @param namePage
     */
    public void navigate(String namePage) {
        PageFactory factory = new PageFactory();
        getSession().getNavigation().remove();
        Page page = factory.getPage(namePage, output, getSession());
        page.move();
    }

    /**
     * getter
     * @return
     */
    public Session getSession() {
        return session;
    }

    /**
     * getter
     * @return
     */
    public ArrayNode getOutput() {
        return output;
    }
    /**
     * setter
     * @param output
     */
    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
