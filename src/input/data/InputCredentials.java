package input.data;

public final class InputCredentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    /**
     * Getter
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter
     *
     * @return user's type of account
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Getter
     *
     * @return user's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter
     *
     * @return user's balance
     */
    public int getBalance() {
        return balance;
    }

}
