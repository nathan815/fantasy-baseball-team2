import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LeagueTest {

    private League league;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeEach() {
        league = new League();
    }

    @Test
    public void getTeam_ValidTeamName_ShouldReturnCorrectTeam() {
        assertEquals("A", league.getTeam("A").getName());
        assertEquals("B", league.getTeam("B").getName());
        assertEquals("C", league.getTeam("C").getName());
        assertEquals("D", league.getTeam("D").getName());
    }

    @Test
    public void getTeam_InvalidTeamName_ShouldReturnNull() {
        assertNull(league.getTeam("fake team"));
    }

    @Test
    public void draftPlayerToTeam_InvalidFirstAndLastName_ShouldThrowException() throws PlayerDraftException {
        exception.expect(PlayerDraftException.class);
        league.draftPlayerToTeam("FakeLast, FakeFirst", "A");
    }

    @Test
    public void draftPlayerToTeam_InvalidTeamName_ShouldThrowException() throws PlayerDraftException {
        exception.expect(PlayerDraftException.class);
        league.draftPlayerToTeam("Cruz", "fake team");
    }

    @Test
    public void draftPlayerToTeam_ValidLastButInvalidFirstName_ShouldThrowException() throws PlayerDraftException {
        exception.expect(PlayerDraftException.class);
        league.draftPlayerToTeam("Cruz, FakeFirst", "A");
    }

    @Test
    public void draftPlayerToTeam_PlayerAlreadyDrafted_ShouldThrowException() throws PlayerDraftException {
        // first we draft Nelson Cruz to team A
        league.draftPlayerToTeam("Cruz", "A");

        // trying to draft him again should throw exception
        exception.expect(PlayerDraftException.class);
        league.draftPlayerToTeam("Cruz", "A");
    }

    @Test
    public void draftPlayerToTeam_PlayerAlreadyDraftedToAnotherTeam_ShouldThrowException() throws PlayerDraftException {
        // first we draft Nelson Cruz to team A
        league.draftPlayerToTeam("Cruz", "A");

        // trying to draft him again to another team should throw exception
        exception.expect(PlayerDraftException.class);
        league.draftPlayerToTeam("Cruz", "B");
    }

    @Test
    public void draftPlayerToTeam_ValidLastName_ShouldDraftPlayer() throws PlayerDraftException {
        league.draftPlayerToTeam("Cruz", "A");
        assertEquals("Cruz", league.getTeam("A").getPlayers().get(0).getLastName());
    }

    @Test
    public void draftPlayerToTeam_ValidLastNameAndFirstInitial_ShouldDraftPlayer() throws PlayerDraftException {
        league.draftPlayerToTeam("Cruz, N", "A");
        assertEquals("Cruz", league.getTeam("A").getPlayers().get(0).getLastName());
    }

    @Test
    public void draftPlayerToTeam_ValidLastNameAndFirstName_ShouldDraftPlayer() throws PlayerDraftException {
        league.draftPlayerToTeam("Cruz, Nelson", "A");
        assertEquals("Cruz", league.getTeam("A").getPlayers().get(0).getLastName());
    }

    @Test
    public void findPlayerByName_UniquePlayerName_ShouldReturnListWithOnePlayer() throws PlayerDraftException {
        List<Player> results = league.findPlayerByName("Cruz");
        assertEquals(1, results.size());
        Player player = results.get(0);
        assertEquals("Nelson", player.getFirstName());
        assertEquals("Cruz", player.getLastName());
    }

    @Test
    public void findPlayerByName_NonUniqueLastName_ShouldReturnListWithMatchingPlayers() {
        // three players: Carlos Santana, Domingo Santana, and Danny Santana
        List<Player> results = league.findPlayerByName("Santana");
        assertEquals(3, results.size());
    }

    @Test
    public void findPlayerByName_NonUniqueLastNameAndFirstInitial_ShouldReturnListWithMatchingPlayers() {
        // two players: Domingo Santana and Danny Santana
        List<Player> results = league.findPlayerByName("Santana, D");
        assertEquals(2, results.size());
    }

    @Test
    public void findPlayerByName_NonUniqueLastNameAndUniqueFirstName_ShouldReturnListWithOnePlayer() {
        List<Player> results1 = league.findPlayerByName("Santana, Danny");
        assertEquals(1, results1.size());
        assertEquals("Danny", results1.get(0).getFirstName());

        List<Player> results2 = league.findPlayerByName("Santana, Domingo");
        assertEquals(1, results2.size());
        assertEquals("Domingo", results2.get(0).getFirstName());
    }

    @Test
    public void findPlayerByName_NonUniqueLastNameAndUniqueFirstInitial_ShouldReturnListWithOnePlayer() {
        List<Player> results = league.findPlayerByName("Santana, C");
        assertEquals(1, results.size());
        assertEquals("Carlos", results.get(0).getFirstName());
    }
}
