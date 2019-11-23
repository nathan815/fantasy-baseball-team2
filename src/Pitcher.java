public class Pitcher extends Player {
    private String era;
    private String strikeOuts;
    private String hitsAllowed;
    private String runsAllowed;


    public Pitcher(String name, String team, String era, String strikeOuts, String hitsAllowed, String runsAllowed){
        super(name, team);
        this.era = era;
        this.strikeOuts = strikeOuts;
        this.hitsAllowed = hitsAllowed;
        this.runsAllowed = runsAllowed;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getStrikeOuts() {
        return strikeOuts;
    }

    public void setStrikeOuts(String strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public String getHitsAllowed() {
        return hitsAllowed;
    }

    public void setHitsAllowed(String hitsAllowed) {
        this.hitsAllowed = hitsAllowed;
    }

    public String getRunsAllowed() {
        return runsAllowed;
    }

    public void setRunsAllowed(String runsAllowed) {
        this.runsAllowed = runsAllowed;
    }
}
