import java.util.Map;

public abstract class Player {
    private String firstName, lastName, playerTeam;
    private boolean isDrafted = false;

    public Player(String firstName, String lastName, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        playerTeam = team;
    }

    public String getPlayerTeam(){
        return playerTeam;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isDrafted() {
        return isDrafted;
    }

    public void setDrafted() {
        isDrafted = true;
    }

    public abstract Map<String, Double> getStatValuesMap();
}
