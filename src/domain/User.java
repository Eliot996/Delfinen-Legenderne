package domain;
// @author Mathias og Sofia
public class User {
    private String username;
    private String password;
    private Roles role;

    public User(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public Boolean matchPassword(String guess) {
        return password.equals(guess);
    }

    public String getUsername() {
        return username;
    }
}
