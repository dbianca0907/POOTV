package actions;

import database.Session;


public class BuyAction extends Strategy {
    public static final int minTokens = 10;

    /**
     * metoda care cumpara tokens si contul premium
     * @return 1, daca ctiunea s-a realizat cu succes,
     *  -1, daca actiunea nu a putut fi executata
     *  0 daca campul feature nu era cel corect
     */
    public int execute() {
        int count = super.getSession().getAction().getCount();
        int nrTokensUser = super.getSession().getCurrentUser().getTokensCount();
        int nrBalance = super.getSession().getCurrentUser().getCredentials().getBalance();
        if (super.getSession().getAction().getFeature().equals("buy tokens")) {
            if (nrBalance < count) {
                return -1;
            }
            super.getSession().getCurrentUser().getCredentials().setBalance(nrBalance - count);
            super.getSession().getCurrentUser().setTokensCount(nrTokensUser + count);
            return 1;
        } else if (super.getSession().getAction().getFeature().equals("buy premium account")) {
            System.out.println("a ajuns in functia de buy premium account");
            if (nrTokensUser < minTokens) {
                return -1;
            }
            if (super.getSession().getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                return -1;
            }
            super.getSession().getCurrentUser().setTokensCount(nrTokensUser - minTokens);
            System.out.println( super.getSession().getCurrentUser().getTokensCount());
            super.getSession().getCurrentUser().getCredentials().setAccountType("premium");
            return 1;
        }
        return 0;
    }
}
