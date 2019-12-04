import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// program to print out all players with same last names to help with testing
public class UtilPlayersWithSameLastName {
    public static void main(String[] args) {
        League league = new League();
        List<Player> allPlayers = Stream.concat(league.getHitters().stream(), league.getPitchers().stream())
                .collect(toList());

        Map<String, Integer> lastNameFrequency = new HashMap<>();
        for (Player p : allPlayers) {
            lastNameFrequency.put(p.getLastName(), lastNameFrequency.getOrDefault(p.getLastName(), 0) + 1);
        }

        List<Player> dupPlayers = allPlayers.stream()
                .filter(player -> lastNameFrequency.get(player.getLastName()) > 1)
                .sorted(Comparator.comparing(Player::getLastName))
                .collect(toList());

        System.out.println("Total Players: " + allPlayers.size());
        System.out.println("Same Last Names: " + dupPlayers.size());
        System.out.println("----");
        dupPlayers.forEach(player -> System.out.println(player.getFirstName() + " " + player.getLastName()));

    }
}
