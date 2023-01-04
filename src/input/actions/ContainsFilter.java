package input.actions;

import java.util.ArrayList;

public class ContainsFilter {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    /**
     * getter
     * @return
     */
    public ArrayList<String> getActors() {
        return actors;
    }
    /**
     * getter
     * @return
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * setter
     * @param actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * setter
     * @param genre
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
