import java.util.HashMap;
import java.util.Map;

public class Pitcher extends Player {
    public static final String INITIAL_STAT = "ERA";
    private double earnedRunAverage;
    private int strikeOuts;
    private int hitsAllowed;
    private int runsAllowed;


    public Pitcher(String firstName, String lastName, String team, double earnedRunAverage, int strikeOuts, int hitsAllowed, int runsAllowed) {
        super(firstName, lastName, team, earnedRunAverage);
        this.earnedRunAverage = earnedRunAverage;
        this.strikeOuts = strikeOuts;
        this.hitsAllowed = hitsAllowed;
        this.runsAllowed = runsAllowed;
    }

    @Override
    public Map<String, Double> getStatValuesMap() {
        Map<String, Double> values = new HashMap<>();
        values.put("ERA", earnedRunAverage);
        values.put("SO", (double)strikeOuts);
        values.put("HA", (double)hitsAllowed);
        values.put("RA", (double)runsAllowed);
        return values;
    }

    public double getEarnedRunAverage() {
        return earnedRunAverage;
    }

    public void setEarnedRunAverage(double earnedRunAverage) {
        this.earnedRunAverage = earnedRunAverage;
    }

    public int getStrikeOuts() {
        return strikeOuts;
    }

    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public int getHitsAllowed() {
        return hitsAllowed;
    }

    public void setHitsAllowed(int hitsAllowed) {
        this.hitsAllowed = hitsAllowed;
    }

    public int getRunsAllowed() {
        return runsAllowed;
    }

    public void setRunsAllowed(int runsAllowed) {
        this.runsAllowed = runsAllowed;
    }

}
