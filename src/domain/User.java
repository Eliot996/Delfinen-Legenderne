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

    public User(String CSV){
        // splits the CSV
        String[] elements = CSV.split(";");

        // assigns base on position
        this.username = elements[0];
        this.password = elements[1];
        this.role = Roles.valueOf(elements[2]);
    }

    public String toCSV(){
        return username + ';' + password + ';' + role;
    }

    @Override
    public String toString() {
        // converts the Enum to an userreadable string
        String roleToPrint;
        switch (role) {
            case ADMIN   -> roleToPrint = "administrator";
            case CASHIER -> roleToPrint = "kasser";
            case TRAINER -> roleToPrint = "træner";
            default -> roleToPrint = "role er ikke blevet defineret";
        }

        return username +  ", role: " + roleToPrint;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
