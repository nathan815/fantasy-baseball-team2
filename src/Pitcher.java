public class Pitcher extends Player {
    private double era;
    private int strikeOuts;
    private int hitsAllowed;
    private int runsAllowed;


    public Pitcher(String name, String team, double era, int strikeOuts, int hitsAllowed, int runsAllowed) {
        super(name, team);
        this.era = era;
        this.strikeOuts = strikeOuts;
        this.hitsAllowed = hitsAllowed;
        this.runsAllowed = runsAllowed;
    }

    public double getEra() {
        return era;
    }

    public void setEra(double era) {
        this.era = era;
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
