package actions.actions_pages;


import actions.strategy_design.Strategy;

public class Buy extends Strategy {
    public static final int minTokens = 10;

    /**
     * Buying tokens or premium account.
     *
     * @return 1, if the action was completed successfully
     *        -1, if an error occured
     *        0, otherwise
     */
    @Override
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
            if (nrTokensUser < minTokens) {
                return -1;
            }
            String type = super.getSession().getCurrentUser().getCredentials().getAccountType();
            if (type.equals("premium")) {
                return -1;
            }
            super.getSession().getCurrentUser().setTokensCount(nrTokensUser - minTokens);
            super.getSession().getCurrentUser().getCredentials().setAccountType("premium");
            return 1;
        }
        return 0;
    }
}
