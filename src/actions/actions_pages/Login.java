package actions.actions_pages;
import actions.strategy_design.Strategy;
import database.User;

public class Login extends Strategy {

    /**
     * metoda care logheaza userul, il seteaza in sesiunea curenta ca fiind "logged"
     * @return -1, daca userul nu se afla in baza de date, 1 altfel
     */
    public int execute() {
        String name = super.getSession().getAction().getCredentials().getName();
        String password = super.getSession().getAction().getCredentials().getPassword();
        if (!super.getSession().getDatabase().getUserHashMap().containsKey(name + password)) {
            return -1;
        }
        User currentUser = super.getSession().getDatabase().getUserHashMap().get(name + password);
        super.getSession().setCurrentUser(currentUser);
        super.getSession().setLogged(true);
        return 1;
    }
}
