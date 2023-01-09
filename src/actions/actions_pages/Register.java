package actions.actions_pages;

import actions.strategy_design.Strategy;
import database.user_data.Credentials;
import database.user_data.User;

public class Register extends Strategy {

    /**
     * Register a new user in the Database.
     * Verifying if it is already registered.
     *
     * @return 1, if the action was completed successfully
     *        -1, otherwise
     */
    @Override
    public int execute() {
        String name = getSession().getAction().getCredentials().getName();
        String password = getSession().getAction().getCredentials().getPassword();

        // The key was made by the user's name and its password

        if (getSession().getDatabase().getUserHashMap().containsKey(name + password)) {
            return -1;
        }
        
        Credentials input = getSession().getAction().getCredentials();
        Credentials newCr = new Credentials(input.getName(), input.getPassword(),
                                            input.getAccountType(),
                                            input.getCountry(), input.getBalance());
        User newUser = new User(newCr);
        getSession().getDatabase().getUsers().add(newUser);
        getSession().getDatabase().addUsers(newUser);
        getSession().setCurrentUser(newUser);
        getSession().setLogged(true);
        return 1;
    }
}
