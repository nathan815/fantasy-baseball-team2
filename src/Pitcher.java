public class Pitcher extends Player {
    private double earnedRunAverage;
    private int strikeOuts;
    private int hitsAllowed;
    private int runsAllowed;


    public Pitcher(String firstName, String lastName, String team, double earnedRunAverage, int strikeOuts, int hitsAllowed, int runsAllowed) {
        super(firstName, lastName, team);
        this.earnedRunAverage = earnedRunAverage;
        this.strikeOuts = strikeOuts;
        this.hitsAllowed = hitsAllowed;
        this.runsAllowed = runsAllowed;
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
