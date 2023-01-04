package input.data;

public final class InputCredentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

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
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * setter
     * @param password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * setter
     * @param accountType
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * setter
     * @param country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * setter
     * @param balance
     */
    public void setBalance(final int balance) {
        this.balance = balance;
    }


}
