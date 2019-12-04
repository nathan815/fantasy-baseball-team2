import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private static final int NUM_HITTERS = 8;
    private static final int NUM_PITCHERS = 5;
    private static final int TEAM_SIZE = NUM_HITTERS + NUM_PITCHERS;

    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>(TEAM_SIZE);
    }

    public Team(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean draftHitter(Hitter hitter) {
        if (!canDraftHitter(hitter)) {
            return false;
        }
        hitter.setDrafted();
        players.add(hitter);
        return true;
    }

    public boolean draftPitcher(Pitcher pitcher) {
        if (!canDraftPitcher(pitcher)) {
            return false;
        }
        pitcher.setDrafted();
        players.add(pitcher);
        return true;
    }

    public List<Pitcher> getPitchers() {
        return players.stream()
                .filter(player -> player instanceof Pitcher)
                .map(player -> (Pitcher) player)
                .collect(Collectors.toList());
    }

    public List<Hitter> getHitters() {
        return players.stream()
                .filter(player -> player instanceof Hitter)
                .map(player -> (Hitter) player)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    // Returns true if a given hitter's position is available, false otherwise
    public boolean isHitterPositionAvailable(String position) {
        for (Hitter hitter : getHitters()) {
            if (hitter.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPitcherAvailable() {
        return getPitchers().size() < NUM_PITCHERS;
    }

    private boolean canDraftHitter(Hitter hitter) {
        return !hitter.isDrafted() && getHitters().size() < NUM_HITTERS && isHitterPositionAvailable(hitter.getPosition());
    }

    private boolean canDraftPitcher(Pitcher pitcher) {
        return !pitcher.isDrafted() && getPitchers().size() < NUM_PITCHERS;
    }
}
