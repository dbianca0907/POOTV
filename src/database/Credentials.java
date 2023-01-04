package database;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    /**
     * constructor
     * @param name
     * @param password
     * @param accountType
     * @param country
     * @param balance
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
     * getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * getter
     * @return
     */
    public String getAccountType() {
        return accountType;
    }
    /**
     * getter
     * @return
     */
    public String getCountry() {
        return country;
    }
    /**
     * getter
     * @return
     */
    public int getBalance() {
        return balance;
    }
    /**
     * setter
     * @return
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * setter
     * @return
     */
    public void setPassword(final String password) {
        this.password = password;
    }
    /**
     * setter
     * @return
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }
    /**
     * setter
     * @return
     */
    public void setCountry(final String country) {
        this.country = country;
    }
    /**
     * setter
     * @return
     */
    public void setBalance(final int balance) {
        this.balance = balance;
    }

}
