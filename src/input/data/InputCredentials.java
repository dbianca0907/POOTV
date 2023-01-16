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
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter
     *
     * @return user type of account
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Getter
     *
     * @return user country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter
     *
     * @return user balance
     */
    public int getBalance() {
        return balance;
    }

}
