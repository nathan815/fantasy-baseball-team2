import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class League {
    private List<Hitter> hitters;
    private List<Pitcher> pitchers;

    private List<Team> teams = Arrays.asList(
            new Team("A"), new Team("B"), new Team("C"), new Team("D")
    );
    // map of name -> team object for easy access by name
    private Map<String, Team> teamsMap = new HashMap<>();

    public League() {
        for (Team team : teams) {
            teamsMap.put(team.getName(), team);
        }
        makeHitters();
        makePitchers();
        Collections.sort(hitters);
        Collections.sort(pitchers);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Team getTeam(String name) {
        return teamsMap.getOrDefault(name, null);
    }

    public Player draftPlayerToTeam(String playerName, String teamName) throws PlayerDraftException {
        List<Player> players = findPlayerByName(playerName);
        if (players.isEmpty()) {
            throw new PlayerDraftException("Player " + playerName + " not found");
        } else if (players.size() > 1) {
            throw new PlayerDraftException("Multiple players with name '" + playerName + "'. " +
                    "Please provide full name in form Last, First (or first initial).\n" +
                    "Players found: \n" +
                    players.stream().map(Player::getNameLastCommaFirst).collect(joining("\n")));
        }
        Player player = players.get(0);
        if (player.isDrafted()) {
            throw new PlayerDraftException("Player is already drafted!");
        }
        Team team = getTeam(teamName);
        if (team == null) {
            throw new PlayerDraftException("Invalid team name " + teamName);
        }
        if (player instanceof Hitter) {
            boolean drafted = team.draftHitter((Hitter) player);
            if (!drafted) {
                throw new PlayerDraftException("Unable to draft hitter " + playerName);
            }
        } else if (player instanceof Pitcher) {
            boolean drafted = team.draftPitcher((Pitcher) player);
            if (!drafted) {
                throw new PlayerDraftException("Unable to draft pitcher " + playerName);
            }
        } else {
            throw new PlayerDraftException("Invalid player");
        }
        return player;
    }

    public List<Player> findPlayerByName(String playerName) {
        String[] nameParts = playerName.split(",");
        String lastName = nameParts[0].trim();
        String firstName = nameParts.length >= 2 ? nameParts[1].trim() : "";
        return findPlayerByLastAndFirstName(lastName, firstName);
    }

    public List<Hitter> overall(String position) {
        List<Hitter> out = new ArrayList<>();
        Team a = getTeam("A");
        if (position != null && a.isHitterPositionAvailable(position)) {
            for (int i = 0; i < hitters.size(); i++) {
                if (hitters.get(i).getPosition().equals(position)) {
                    out.add(hitters.get(i));
                }
            }
            return out;
        } else if (position == null) {
            for (Hitter hitter : hitters) {
                if (a.isHitterPositionAvailable(hitter.getPosition()))
                    out.add(hitter);
            }
            return out;
        } else {
            return out;
        }
    }

    public List<Pitcher> pOverall() {
        List<Pitcher> out = new ArrayList<>();
        if (getTeam("A").isPitcherAvailable()) {
            out.addAll(pitchers);
            return out;
        } else {
            return out;
        }
    }

    private List<Player> findPlayerByLastAndFirstName(String lastName, String firstName) {
        return Stream.concat(hitters.stream(), pitchers.stream())
                .filter(player -> player.getLastName().equals(lastName))
                .filter(player -> firstName.isEmpty() || player.getFirstName().startsWith(firstName))
                .collect(toList());
    }

    private void makePitchers() {
        try {
            InputStream reader = this.getClass().getResourceAsStream("pitchers.json");
            JsonReader jReader = new JsonReader(new InputStreamReader(reader, "UTF-8"));
            try {
                pitchers = readPitchersArray(jReader);
            } finally {
                jReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Pitcher> readPitchersArray(JsonReader reader) throws IOException {
        List<Pitcher> pitchers = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            pitchers.add(readPitcher(reader));
        }
        reader.endArray();
        return pitchers;
    }

    private Pitcher readPitcher(JsonReader reader) throws IOException {
        String firstName = null;
        String lastName = null;
        String team = null;
        double earnedRunAverage = 0.0;
        int strikeOuts = 0;
        int hitsAllowed = 0;
        int runsAllowed = 0;

        reader.beginObject();
        while (reader.hasNext()) {
            String data = reader.nextName();
            switch (data) {
                case "name_display_first_last":
                    // pitchers weirdly doesn't have name_first field
                    String[] nameParts = reader.nextString().split(" ");
                    firstName = nameParts[0];
                    lastName = nameParts[1];
                    break;
                case "team_abbrev":
                    team = reader.nextString();
                    break;
                case "era":
                    earnedRunAverage = Double.parseDouble(reader.nextString());
                    break;
                case "so":
                    strikeOuts = Integer.parseInt(reader.nextString());
                    break;
                case "h":
                    hitsAllowed = Integer.parseInt(reader.nextString());
                    break;
                case "r":
                    runsAllowed = Integer.parseInt(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Pitcher(firstName, lastName, team, earnedRunAverage, strikeOuts, hitsAllowed, runsAllowed);
    }

    private void makeHitters() {
        try {
            InputStream reader = this.getClass().getResourceAsStream("hitters.json");
            JsonReader jReader = new JsonReader(new InputStreamReader(reader, "UTF-8"));
            try {
                hitters = readHittersArray(jReader);
            } finally {
                jReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Hitter> readHittersArray(JsonReader reader) throws IOException {
        List<Hitter> hitters = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            hitters.add(readHitter(reader));
        }
        reader.endArray();
        return hitters;
    }


    private Hitter readHitter(JsonReader reader) throws IOException {
        String firstName = null;
        String lastName = null;
        String team = null;
        String position = null;
        double avg = 0.0;
        int hits = 0;
        int runs = 0;
        int runBattedIns = 0;

        reader.beginObject();
        while (reader.hasNext()) {
            String data = reader.nextName();
            switch (data) {
                case "name_first":
                    firstName = reader.nextString();
                    break;
                case "name_last":
                    lastName = reader.nextString();
                    break;
                case "team_abbrev":
                    team = reader.nextString();
                    break;
                case "pos":
                    position = reader.nextString();
                    break;
                case "avg":
                    avg = Double.parseDouble(reader.nextString());
                    break;
                case "h":
                    hits = Integer.parseInt(reader.nextString());
                    break;
                case "r":
                    runs = Integer.parseInt(reader.nextString());
                    break;
                case "rbi":
                    runBattedIns = Integer.parseInt(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Hitter(firstName, lastName, team, position, avg, hits, runs, runBattedIns);
    }

    public List<Hitter> getHitters() {
        return hitters;
    }

    public List<Pitcher> getPitchers() {
        return pitchers;
    }

}
