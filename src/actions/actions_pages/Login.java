package actions.actions_pages;
import actions.strategy_design.Strategy;
import database.user_data.User;

public class Login extends Strategy {

    /**
     * Logging the user based on a specific recognition(key).
     *
     * @return 1, if the action was completed successfully
     *        -1, otherwise
     */
    @Override
    public int execute() {
        String name = getSession().getAction().getCredentials().getName();
        String password = getSession().getAction().getCredentials().getPassword();

        // The key was made by the user name and its password

        if (!getSession().getDatabase().getUserHashMap().containsKey(name + password)) {
            return -1;
        }

        User currentUser = getSession().getDatabase().getUserHashMap().get(name + password);
        getSession().setCurrentUser(currentUser);
        getSession().setLogged(true);
        return 1;
    }
}
