package actions.pages_actions;

import actions.Strategy;
import database.Credentials;
import database.User;

public class Register extends Strategy {

    /**
     * se inregistreaza noul user, dupa ce este verificat sa nu existe deja in baza de date
     * @return -1, daca este deja inregistrat in baza de date
     *          1, daca inregistrarea s-a realizat cu succes
     */
    public int execute() {
        String name = super.getSession().getAction().getCredentials().getName();
        String password = super.getSession().getAction().getCredentials().getPassword();

        if (super.getSession().getDatabase().getUserHashMap().containsKey(name + password)) {
            return -1;
        }
        Credentials input = super.getSession().getAction().getCredentials();
        Credentials newCr = new Credentials(input.getName(), input.getPassword(),
                                            input.getAccountType(),
                                            input.getCountry(), input.getBalance());
        User newUser = new User(newCr);
        super.getSession().getDatabase().getUsers().add(newUser);
        super.getSession().getDatabase().addUsers(newUser);
        super.getSession().setCurrentUser(newUser);
        super.getSession().setLogged(true);
        return 1;
    }
}
