import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;

public class Driver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        League league = new League();
        Expression currentHitterExpression = null, currentPitcherExpression = null;
        boolean run = true;

        while (run) {
            menu();

            System.out.println("Enter your command: ");
            String userInputStr = input.nextLine();
            System.out.println();

            // split user input
            String[] userInput = userSplit(userInputStr);

            // Verify user request
            String request = order(userInput[0].toUpperCase());

            String playerName, teamName = "", position, file;

            switch (request) {
                case "ODRAFT":
                case "IDRAFT":
                    if (userInput.length <= 1) {
                        System.out.println("Error: Must provide player name in format: Last OR Last, F OR Last, First");
                    } else if (request.equals("ODRAFT") && userInput.length < 3) {
                        System.out.println("Error: Must provide league member name");
                    } else {
                        playerName = userInput[1];
                        teamName = request.equals("IDRAFT") ? "A" : userInput[2];
                        try {
                            Player draftedPlayer = league.draftPlayerToTeam(playerName, teamName);
                            System.out.println("Successfully drafted player " + draftedPlayer.getFullName() +
                                    " (" + draftedPlayer.getPosition() + ")");
                        } catch (PlayerDraftException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case "OVERALL":
                    if (userInput.length > 1) {
                        position = userInput[1];
                        List<Hitter> ranked = league.overall(position);
                        if(!Hitter.POSITIONS.contains(position)) {
                            System.out.println("Invalid position.");
                        } else if (ranked.size() == 0) {
                            System.out.println("Position already drafted to your team.");
                        } else {
                            printHitters(ranked, currentHitterExpression);
                        }
                    } else {
                        List<Hitter> ranked = league.overall(null);
                        if (ranked.size() == 0) {
                            System.out.println("No positions available to draft.");
                        } else {
                            printHitters(ranked, currentHitterExpression);
                        }
                    }
                    break;

                case "POVERALL":
                    List<Pitcher> ranked = league.pOverall();
                    if (ranked.size() == 0) {
                        System.out.println("Max pitchers already drafted");
                    } else {
                        printPitchers(ranked, currentPitcherExpression);
                    }
                    break;

                case "TEAM":
                    if (userInput.length>1) {
                        teamName = userInput[1];
                        Team t1 = league.getTeam(teamName);
                        for (String pos:Hitter.POSITIONS){
                            for (Hitter hitter:t1.getHitters()){
                                if(pos.equals(hitter.getPosition())){
                                    System.out.println(pos + " " + hitter.getLastName() + ", " + hitter.getFirstName());

                                }
                                else{
                                    System.out.print("");
                                }
                            }
                        }
                        for (Pitcher pitcher : t1.getPitchers()){
                            System.out.println("P " + pitcher.getLastName() + ", " + pitcher.getFirstName());
                        }
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    break;

                case "STARS":
                    if (userInput.length>1) {
                        teamName = userInput[1];
                        Team t = league.getTeam(teamName);
                        for (Player player : t.getPlayers()) {
                            System.out.println(player.getPosition() + " " + player.getLastName() + ", " + player.getFirstName());
                        }
                    }

                    else {
                        System.out.println("Invalid entry");
                    }
                    break;

                case "SAVE":
                    file = userInput[1];
                    saveState(file, league);
                    break;

                case "RESTORE":
                    file = userInput[1];
                    restoreState(file, league);
                    break;

                case "EVALFUN":
                case "PEVALFUN":
                    if (userInput.length > 1) {
                        String expressionStr = getExpressionFromUserInput(userInput);
                        // EVALFUN is for hitters, PEVALFUN is for pitchers
                        boolean isHitters = request.equals("EVALFUN");
                        List<? extends Player> players = isHitters ? league.getHitters() : league.getPitchers();
                        Expression expression = new Expression(ExpressionTokenizer.tokenize(expressionStr));
                        // Keep track of our current expression for hitter/pitcher
                        if (isHitters) {
                            currentHitterExpression = expression;
                        } else {
                            currentPitcherExpression = expression;
                        }
                        ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);
                        try {
                            // Evaluate the expression for each player and set player's valuation to result
                            for (Player player : players) {
                                double valuation = evaluator.evaluate(player);
                                player.setValuation(valuation);
                            }
                            Collections.sort(players);
                        } catch (ExpressionEvaluationException e) {
                            System.out.println("Evaluation Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Error: must provide expression!");
                    }
                    break;

                case "QUIT":
                    run = false;
                    System.out.print("Do you want to save the current draft before quitting? (Y/N): ");
                    if(input.next().equalsIgnoreCase("Y")) {
                        System.out.print("Enter file name: ");
                        String fileName = input.next();
                        saveState(fileName, league);
                    }
                    break;
                default:
                    System.out.println("Invalid command");

            }
        }

        System.out.println("Program is terminated.");
    }

    // method will return user input as an array of strings
    public static String[] userSplit(String str) {
        String[] userArray = str.split("\\s+");

        if (userArray[0].toUpperCase().equals("ODRAFT")) {
            String[] arr = str.split("\"");

            String req = arr[0].replaceAll("\\s+", "");
            arr[0] = req;

            String name = arr[2].replaceAll("\\s+", "");
            arr[2] = name;

            return arr;
        } else if (userArray[0].toUpperCase().equals("IDRAFT")) {
            String[] arr = str.split("\"");

            String req = arr[0].replaceAll("\\s+", "");
            arr[0] = req;

            return arr;
        } else
            return userArray;
    }

    // method will return the order/request
    public static String order(String userInput) {
        String[] orders = {"ODRAFT", "IDRAFT", "OVERALL", "POVERALL", "TEAM", "STARS", "SAVE", "QUIT", "RESTORE",
                "EVALFUN", "PEVALFUN"};
        for (int i = 0; i < orders.length; i++) {
            if (userInput.equals(orders[i].toUpperCase()))
                return orders[i];
        }
        return "";
    }

    public static void menu() {
        System.out.println("\nMenu:\n-ODRAFT\n-IDRAFT\n-OVERALL\n-POVERALL\n-TEAM\n-STARS\n-SAVE\n-QUIT"
                + "\n-RESTORE\n-EVALFUN\n-PEVALFUN\n");
    }

    private static final String PLAYER_PRINT_FORMAT = "%-30s%-15s%-15s%s\n";

    private static void printPlayerHeader(String valuationExpr) {
        System.out.printf(PLAYER_PRINT_FORMAT, "Name", "Team", "Position", "Valuation (" + valuationExpr + ")");
    }

    private static void printHitters(List<Hitter> hitters, Expression hitterExpression) {
        printPlayerHeader(hitterExpression == null ? Hitter.INITIAL_STAT : hitterExpression.toString());
        for (Hitter h : hitters) {
            System.out.printf(PLAYER_PRINT_FORMAT, h.getNameLastCommaFirst(), h.getPlayerTeam(), h.getPosition(), h.getValuation());
        }
    }

    private static void printPitchers(List<Pitcher> pitchers, Expression pitcherExpression) {
        printPlayerHeader(pitcherExpression == null ? Pitcher.INITIAL_STAT : pitcherExpression.toString());
        for (Pitcher p : pitchers) {
            System.out.printf(PLAYER_PRINT_FORMAT, p.getNameLastCommaFirst(), p.getPlayerTeam(), p.getPosition(), p.getValuation());
        }
    }

    private static String getExpressionFromUserInput(String[] userInput) {
        return Arrays.stream(userInput).skip(1).collect(joining(""));
    }

    private static void saveState(String fileName, League league) {
        String saveFileName = fileName + ".fantasy.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFileName, false))) {
            for (Team team : league.getTeams()) {
                writer.write("-" + team.getName());
                writer.newLine();
                for (Player player : team.getPlayers()) {
                    writer.write(player.getLastName() + "," + player.getFirstName());
                    writer.newLine();
                }
            }
            System.out.println("The state of the system has been saved to " + saveFileName);
        } catch (IOException e) {
            System.out.println("Unable to save the state of the system to a file named " +
                    saveFileName + ": " + e.getMessage());
        }
    }

    private static void restoreState(String fileName, League league) {
        String restoreFileName = fileName + ".fantasy.txt";
        String teamName = null;
        try (BufferedReader br = new BufferedReader(new FileReader(restoreFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '-')
                    teamName = line.substring(1);
                else
                    league.draftPlayerToTeam(line, teamName);
            }
            System.out.println("The state of the system has been restored from " + restoreFileName);
        } catch (IOException | PlayerDraftException e) {
            System.out.println("Unable to restore the state of the system from a file named " +
                    restoreFileName + ": " + e.getMessage());
        }
    }
}
