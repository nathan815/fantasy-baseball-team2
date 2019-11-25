import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class League {
    private List<Hitter> hitters;
    private List<Pitcher> pitchers;

    public League() {
        makeHitters();
        makePitchers();
            System.out.println("Made it here");
    }

    private void setHitters(List<Hitter> hitters) {
        this.hitters = hitters;
    }

    private List<Hitter> getHitters() {
        return hitters;
    }

    private void setPitchers(List<Pitcher> pitchers) {
        this.pitchers = pitchers;
    }

    private List<Pitcher> getPitchers() {
        return pitchers;
    }

    private void makePitchers(){
        try {
            InputStream reader = this.getClass().getResourceAsStream("pitchers.json");
            JsonReader jReader = new JsonReader(new InputStreamReader(reader, "UTF-8"));
            try {
                setPitchers(readPitchersArray(jReader));
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
        while(reader.hasNext()){
            pitchers.add(readPitcher(reader));
        }
        reader.endArray();
        return pitchers;
    }

    private Pitcher readPitcher(JsonReader reader) throws IOException{
        String name = null;
        String team = null;
        String era = null;
        String strikeOuts = null;
        String hitsAllowed = null;
        String runsAllowed = null;

        reader.beginObject();
        while(reader.hasNext()){
            String data = reader.nextName();
            switch (data) {
                case "name_display_first_last":
                    name = reader.nextString();
                    break;
                case "team_abbrev":
                    team = reader.nextString();
                    break;
                case "era":
                    era = reader.nextString();
                    break;
                case "so":
                    strikeOuts = reader.nextString();
                    break;
                case "h":
                    hitsAllowed = reader.nextString();
                    break;
                case "r":
                    runsAllowed = reader.nextString();
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
                setHitters(readHittersArray(jReader));
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
        while(reader.hasNext()){
            hitters.add(readHitter(reader));
        }
        reader.endArray();
        return hitters;
    }


    private Hitter readHitter(JsonReader reader) throws IOException{
        String name = null;
        String team = null;
        String position = null;
        String avg = null;
        String hits = null;
        String runs = null;
        String rbis = null;

        reader.beginObject();
        while(reader.hasNext()){
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
                    avg = reader.nextString();
                    break;
                case "h":
                    hits = reader.nextString();
                    break;
                case "r":
                    runs = reader.nextString();
                    break;
                case "rbi":
                    rbis = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Hitter(name, team, position, avg, hits, runs, rbis);
    }
}
