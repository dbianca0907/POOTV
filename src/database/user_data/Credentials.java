package database.user_data;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    /**
     * Constructor
     *
     * @param name user name
     * @param password user password
     * @param accountType user type of account
     * @param country user country
     * @param balance user balance
     */
    public Credentials(final String name,
                       final String password,
                       final String accountType,
                       final String country,
                       final int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

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

    /**
     * Setter
     *
     * @return user name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter
     *
     * @return user password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Setter
     *
     * @return user type of account
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Setter
     *
     * @return user country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Setter
     *
     * @return user balance
     */
    public void setBalance(final int balance) {
        this.balance = balance;
    }

}
