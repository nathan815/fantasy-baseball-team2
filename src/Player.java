public abstract class Player {
    private String playerName, playerTeam;

    public Player(String name, String team){
        playerName = name;
        playerTeam = team;
    }

    public String getPlayerTeam(){
        return playerTeam;
    }

    public String getPlayerName(){
        return playerName;
    }
}
