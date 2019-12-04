import java.util.HashMap;
import java.util.Map;

public class Hitter extends Player {
    public static final String INITIAL_STAT = "AVG";

    private String position;
    private double avg;
    private int hits;
    private int runs;
    private int runBattedIns;

    public Hitter(String firstName, String lastName, String team, String position, double avg, int hits, int runs, int runBattedIns) {
        super(firstName, lastName, team, avg);
        this.position = position;
        this.avg = avg;
        this.hits = hits;
        this.runs = runs;
        this.runBattedIns = runBattedIns;
    }

    @Override
    public Map<String, Double> getStatValuesMap() {
        Map<String, Double> values = new HashMap<>();
        values.put("AVG", avg);
        values.put("H", (double) hits);
        values.put("R", (double) runs);
        values.put("RBI", (double) runBattedIns);
        return values;
    }

    @Override
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getRunBattedIns() {
        return runBattedIns;
    }

    public void setRunBattedIns(int runBattedIns) {
        this.runBattedIns = runBattedIns;
    }
}
