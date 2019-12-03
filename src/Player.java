import java.util.Map;

public abstract class Player implements Comparable<Player> {
    private String firstName, lastName, playerTeam;
    private double valuation;
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

    public double getValuation() {
        return valuation;
    }

    public void setValuation(double valuation) {
        this.valuation = valuation;
    }

    @Override
    public int compareTo(Player other) {
        return Double.compare(other.valuation, valuation);
    }
}
