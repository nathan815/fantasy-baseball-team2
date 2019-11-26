import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Team getTeam(String name) {
        return teamsMap.getOrDefault(name, null);
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
        String name = null;
        String team = null;
        double era = 0.0;
        int strikeOuts = 0;
        int hitsAllowed = 0;
        int runsAllowed = 0;

        reader.beginObject();
        while (reader.hasNext()) {
            String data = reader.nextName();
            switch (data) {
                case "name_display_first_last":
                    name = reader.nextString();
                    break;
                case "team_abbrev":
                    team = reader.nextString();
                    break;
                case "era":
                    era = Double.parseDouble(reader.nextString());
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
        return new Pitcher(name, team, era, strikeOuts, hitsAllowed, runsAllowed);
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
        String name = null;
        String team = null;
        String position = null;
        double avg = 0.0;
        int hits = 0;
        int runs = 0;
        int rbis = 0;

        reader.beginObject();
        while (reader.hasNext()) {
            String data = reader.nextName();
            switch (data) {
                case "name_display_first_last":
                    name = reader.nextString();
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
                    rbis = Integer.parseInt(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Hitter(name, team, position, avg, hits, runs, rbis);
    }

    private List<Hitter> getHitters() {
        return hitters;
    }
    private List<Pitcher> getPitchers() {
        return pitchers;
    }
}
