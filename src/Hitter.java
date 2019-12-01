public class Hitter extends Player {
    private String position;
    private double avg;
    private int hits;
    private int runs;
    private int runBattedIns;

    public Hitter(String firstName, String lastName, String team, String position, double avg, int hits, int runs, int runBattedIns) {
        super(firstName, lastName, team);
        this.position = position;
        this.avg = avg;
        this.hits = hits;
        this.runs = runs;
        this.runBattedIns = runBattedIns;
    }

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
