import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TeamTest {

    @Test
    public void draftHitter_PositionAvailable_ShouldDraftHitter() {
        Hitter newHitter = makeHitterWithPosition("RF");
        Team team = new Team(makeList(makeHitterWithPosition("CF")));
        assertTrue(team.draftHitter(newHitter));
        assertTrue(newHitter.isDrafted());
        assertEquals(2, team.getPlayers().size());
    }

    @Test
    public void draftHitter_PositionNotAvailable_ShouldNotDraftHitter() {
        Hitter newHitter = makeHitterWithPosition("RF");
        Team team = new Team(makeList(makeHitterWithPosition("RF")));
        assertFalse(team.draftHitter(newHitter));
        assertFalse(newHitter.isDrafted());
        assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void draftHitter_HitterAlreadyDrafted_ShouldNotDraftHitter() {
        Team team = new Team(makeList());
        Hitter hitter = makeHitter();
        hitter.setDrafted();
        assertFalse(team.draftHitter(hitter));
    }

    @Test
    public void draftPitcher_TeamWithLessThan5Pitchers_ShouldDraftPitcher() {
        Team team = new Team(makeList(makePitcher(), makePitcher()));
        Pitcher p = makePitcher();
        assertTrue(team.draftPitcher(p));
        assertTrue(p.isDrafted());
        assertEquals(3, team.getPlayers().size());
    }

    @Test
    public void draftPitcher_TeamWith5Pitchers_ShouldNotDraftPitcher() {
        Team team = new Team(makeList(makePitcher(), makePitcher(), makePitcher(), makePitcher(), makePitcher()));
        Pitcher p = makePitcher();
        assertFalse(team.draftPitcher(p));
        assertFalse(p.isDrafted());
        assertEquals(5, team.getPlayers().size());
    }

    @Test
    public void draftPitcher_PitcherAlreadyDrafted_ShouldNotDraftPitcher() {
        Team team = new Team(makeList());
        Pitcher pitcher = makePitcher();
        pitcher.setDrafted();
        assertFalse(team.draftPitcher(pitcher));
    }

    @Test
    public void getPitchers_ListOfPlayers_ShouldReturnPitchers() {
        Pitcher pitcher = makePitcher();
        Team team = new Team(makeList(makeHitter(), pitcher));
        List<Pitcher> expectedList = Collections.singletonList(pitcher);
        assertThat(team.getPitchers(), is(expectedList));
    }

    @Test
    public void getHitters_ListOfPlayers_ShouldReturnHitters() {
        Hitter hitter = makeHitter();
        Team team = new Team(makeList(hitter, makePitcher()));
        List<Hitter> expectedList = Collections.singletonList(hitter);
        assertThat(team.getHitters(), is(expectedList));
    }

    // Utility methods for testing

    private static Hitter makeHitter() {
        return makeHitterWithPosition("SS");
    }

    private static Hitter makeHitterWithPosition(String pos) {
        return new Hitter("firstname" + pos, "lastname", "team", pos, 0.5, 1, 1, 1);
    }

    private static Pitcher makePitcher() {
        return new Pitcher("firstname", "lastname", "team", 1.0, 1, 1, 1);
    }

    private static <T> List<T> makeList(T ...items) {
        return new ArrayList<>(Arrays.asList(items));
    }
}
