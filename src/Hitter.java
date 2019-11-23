public class Hitter extends Player {
    private String position;
    private String avg;
    private String hits;
    private String runs;
    private String rbis;

    public Hitter(String name, String team, String position, String avg, String hits, String runs, String rbis){
        super(name, team);
        this.position = position;
        this.avg = avg;
        this.hits = hits;
        this.runs = runs;
        this.rbis = rbis;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getRbis() {
        return rbis;
    }

    public void setRbis(String rbis) {
        this.rbis = rbis;
    }
}
