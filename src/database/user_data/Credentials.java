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
     * @param name user's name
     * @param password user's password
     * @param accountType user's type of account
     * @param country user's country
     * @param balance user's balance
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

    /**
     * Setter
     *
     * @return user's name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter
     *
     * @return user's password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Setter
     *
     * @return user's type of account
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Setter
     *
     * @return user's country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Setter
     *
     * @return user's balance
     */
    public void setBalance(final int balance) {
        this.balance = balance;
    }

}
