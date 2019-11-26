import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TeamTest {

    @Test
    public void canDraftHitter_returnsTrueWhenHitterPositionAvailable() {
        Hitter newHitter = makeHitterWithPosition("RF");
        Team team = new Team(Collections.singletonList(makeHitterWithPosition("CF")));
        assertTrue("can draft hitter when position is not yet taken", team.canDraftHitter(newHitter));
    }

    @Test
    public void canDraftHitter_returnsFalseWhenHitterPositionNotAvailable() {
        Hitter newHitter = makeHitterWithPosition("RF");
        Team team = new Team(Collections.singletonList(makeHitterWithPosition("RF")));
        assertFalse("cannot draft hitter when position is taken", team.canDraftHitter(newHitter));
    }

    @Test
    public void canDraftPitcher_returnsTrueWhenLessThan5Pitchers() {
        Team team = new Team(Arrays.asList(makePitcher(), makePitcher()));
        assertTrue(team.canDraftPitcher());
    }

    @Test
    public void canDraftPitcher_returnsFalseWhen5PitchersReached() {
        Team team = new Team(Arrays.asList(makePitcher(), makePitcher(), makePitcher(), makePitcher(), makePitcher()));
        assertFalse(team.canDraftPitcher());
    }

    @Test
    public void draftHitter() {
        // TODO
    }

    @Test
    public void draftPitcher() {
        // TODO
    }

    @Test
    public void getPitchers_returnsListOfPitchersFromPlayers() {
        Pitcher pitcher = makePitcher();
        Team team = new Team(Arrays.asList(makeHitter(), pitcher));
        List<Pitcher> expectedList = Collections.singletonList(pitcher);
        assertThat(team.getPitchers(), is(expectedList));
    }

    @Test
    public void getHitters_returnsListOfHittersFromPlayers() {
        Hitter hitter = makeHitter();
        Team team = new Team(Arrays.asList(hitter, makePitcher()));
        List<Hitter> expectedList = Collections.singletonList(hitter);
        assertThat(team.getHitters(), is(expectedList));
    }

    // Utility methods for testing

    private static Hitter makeHitter() {
        return makeHitterWithPosition("SS");
    }

    private static Hitter makeHitterWithPosition(String pos) {
        return new Hitter("name" + pos, "team", pos, 0.5, 1, 1, 1);
    }

    private static Pitcher makePitcher() {
        return new Pitcher("name", "team", 1.0, 1, 1, 1);
    }
}
