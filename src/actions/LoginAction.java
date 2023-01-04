package actions;

import database.Session;
import database.User;

public class LoginAction extends Strategy {

    /**
     * metoda care logheaza userul, il seteaza in sesiunea curenta ca fiind "logged"
     * @return -1, daca userul nu se afla in baza de date, 1 altfel
     */
    public int execute() {
        if (!super.getSession().getDatabase().getUserHashMap().containsKey("Iancucorega"))
            System.out.println("NU CONTINE USERUL");
        String name = super.getSession().getAction().getCredentials().getName();
        String password = super.getSession().getAction().getCredentials().getPassword();
        System.out.println(name + password);
        if (!super.getSession().getDatabase().getUserHashMap().containsKey(name + password)) {
            return -1;
        }
        User currentUser = super.getSession().getDatabase().getUserHashMap().get(name + password);
        super.getSession().setCurrentUser(currentUser);
        super.getSession().setLogged(true);
        return 1;
    }
}
