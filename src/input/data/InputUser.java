package input.data;

public class InputUser {
    private InputCredentials credentials;

    /**
     * Getter
     *
     * @return user credentials
     */

    public InputCredentials getCredentials() {
        return credentials;
    }

    /**
     * Setter
     *
     * @param credentials user credentials
     */
    public void setCredentials(final InputCredentials credentials) {
        this.credentials = credentials;
    }
}
