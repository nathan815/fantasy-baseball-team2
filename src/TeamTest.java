import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void canDraftHitter_returnsTrueWhenHitterPositionsAvailable() {
        List<Player> players = new ArrayList<>();
        Hitter hitter = new Hitter("1", "team", "pos", 0.5, 1, 1, 1);
        players.add(hitter);
        Team team = new Team(players);
        assertTrue(team.canDraftHitter(hitter));
    }

    @Test
    public void draftHitter() {
    }

    @Test
    public void canDraftPitcher() {
    }

    @Test
    public void draftPitcher() {
    }

    @Test
    public void getPitchers() {
    }

    @Test
    public void getHitters() {
    }
}