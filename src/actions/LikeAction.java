package actions;

import database.Movie;
public class LikeAction extends Strategy {

    /**
     * metoda care creste numarul de like - uri al filmului din lista generala din DataBase
     */
    public int execute() {
        if (super.getSession().getOldFeature().equals("watch")) {
            System.out.println("a intrat in if-ul de la Like");
            for (Movie movie : super.getSession().getDatabase().getAllMovies()) {
                if (movie.getName().equals(super.getSession().getNameCurrMovie())) {
                    int numLikes = movie.getNumLikes() + 1;
                    movie.setNumLikes(numLikes);
                    super.getSession().setOldFeature("like");
                    return 1;
                }
            }
        }
        return -1;
    }
}
