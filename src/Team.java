import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private static final int MAX_HITTERS = 8;
    private static final int MAX_PITCHERS = 5;
    private static final int MAX_PLAYERS = MAX_HITTERS + MAX_PITCHERS;

    private List<Player> players;

    public Team() {
        players = new ArrayList<>();
    }

    public Team(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // Returns true if a given hitter's position is available, false otherwise
    private boolean isHitterPositionAvailable(Hitter hitter) {
        for(Hitter otherHitter : getHitters()) {
            // TODO use the Hitter's position name here once data PR is merged
            if(otherHitter.getPlayerName().equals(hitter.getPlayerName())) {
                return false;
            }
        }
        return true;
    }

    public boolean canDraftHitter(Hitter hitter) {
        return getHitters().size() < MAX_HITTERS && isHitterPositionAvailable(hitter);
    }

    public void draftHitter(Hitter hitter) {
        if (!canDraftHitter(hitter)) {
            throw new RuntimeException("Hitter position " + hitter + " is filled.");
        }
        players.add(hitter);
    }

    public boolean canDraftPitcher() {
        return getPitchers().size() < MAX_HITTERS;
    }

    public void draftPitcher(Pitcher pitcher) {
        if (!canDraftPitcher()) {
            throw new RuntimeException("All pitcher positions filled.");
        }
        players.add(pitcher);
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
}
