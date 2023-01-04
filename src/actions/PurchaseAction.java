package actions;

import database.Session;

public class PurchaseAction extends Strategy {

    /**
     * metoda pentru cumpoararea filmului
     * @return -1, daca userul premium nu mai are filme gratis si nici tokens ca sa cumpere
     *          -1, daca userul standard nu are tokens ca sa cumpere filmul
     *          1, daca actiunea s-a realizat cu succes
     */
    public int execute() {
        if (super.getSession().getOldFeature().equals("watch")
            || super.getSession().getOldFeature().equals("like")
            || super.getSession().getOldFeature().equals("rate"))
            return -1;
        if (super.getSession().getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            if (super.getSession().getCurrentUser().getNumFreePremiumMovies() <= 0) {
                if (super.getSession().getCurrentUser().getTokensCount() < 2) {
                    return -1;
                }
                int nrTokens = super.getSession().getCurrentUser().getTokensCount();
                super.getSession().getCurrentUser().setTokensCount(nrTokens - 2);
                super.getSession().setOldFeature("purchase");
                return 1;
            }
            int nrMoviesFree = super.getSession().getCurrentUser().getNumFreePremiumMovies();
            super.getSession().getCurrentUser().setNumFreePremiumMovies(nrMoviesFree - 1);
            super.getSession().setOldFeature("purchase");
            return 1;
        } else if (super.getSession().getCurrentUser().getCredentials().getAccountType().equals("standard")) {
            if (super.getSession().getCurrentUser().getTokensCount() < 2) {
                return -1;
            }
            int nrTokens = super.getSession().getCurrentUser().getTokensCount();
            super.getSession().getCurrentUser().setTokensCount(nrTokens - 2);
            super.getSession().setOldFeature("purchase");
            return 1;
        }
        return -1;
    }
}
