public abstract class Player {
    private String playerName, playerTeam;
    private boolean isDrafted = false;

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

    public boolean isDrafted() {
        return isDrafted;
    }

    public void setDrafted() {
        isDrafted = true;
    }

}
