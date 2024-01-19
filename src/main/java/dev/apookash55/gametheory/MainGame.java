package dev.apookash55.gametheory;

import dev.apookash55.definition.Decision;
import dev.apookash55.definition.Player;
import dev.apookash55.definition.Result;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainGame {

    private static final int COOPERATIVE_WIN = 2;
    private static final int DEFECTIVE_WIN = 1;
    private static final int ABSOLUTE_WIN = 3;
    private static final int ABSOLUTE_LOSS = 0;
    private static final int TOTAL_ROUNDS = 200;
    private static final String PLAYERS_PATH = "/src/main/java/dev/apookash55/gametheory/players/";
    private static final String RESULTS_PATH = "/src/main/resources/";
    private static final String PACKAGE_PATH = "dev.apookash55.gametheory.players.";

    public static void main(String[] args) throws Exception {

        Path workingDir = Paths.get("");
        String pathString = workingDir.toAbsolutePath() + PLAYERS_PATH;
        List<String> files = listFilesUsingJavaIO(pathString);
        files.replaceAll(s -> s.split("\\.")[0]);

        Player[] players = new Player[files.size()];
        HashMap<String, Integer> playersScores = new HashMap<>();
        int totalPlayers = files.size();

        for (int i = 0; i < totalPlayers; i++) {
            String className = PACKAGE_PATH + files.get(i);
            players[i] = getInstance(className);
            playersScores.put(files.get(i), 0);
        }

        int count = 1;
        StringBuilder result = new StringBuilder("Player, Total Score\n");
        StringBuilder matches = new StringBuilder("Game, Player 1, Player 1 Score, Player 2, Player 2 Score\n");

        for(int i = 0; i < totalPlayers; i++) {
            for(int j = i + 1; j < totalPlayers; j++) {
                Result res = playGame(players[i], players[j]);

                playersScores.put(files.get(i), playersScores.get(files.get(i)) + res.getPlayer1());
                playersScores.put(files.get(j), playersScores.get(files.get(j)) + res.getPlayer2());

                matches.append(count).append(", ")
                        .append(files.get(i)).append(", ").append(res.getPlayer1()).append(", ")
                        .append(files.get(j)).append(", ").append(res.getPlayer2()).append("\n");
                writeToCSV("games", (count + "_" + files.get(i) + "VS" + files.get(j)), res.getAttempts());

                players[i] = getInstance(PACKAGE_PATH + files.get(i));
                players[j] = getInstance(PACKAGE_PATH + files.get(j));

                ++count;
            }
        }

        playersScores = sortByValue(playersScores);

        System.out.println("------ Final Score ------");
        for(String player : playersScores.keySet()) {
            System.out.println(player + " : " + playersScores.get(player));
            result.append(player).append(", ").append(playersScores.get(player)).append("\n");
        }
        writeToCSV(null, "results", result.toString());
        writeToCSV(null, "games", matches.toString());

        System.out.println("\nResults generated in " + workingDir.toAbsolutePath() + RESULTS_PATH);
    }

    private static void writeToCSV(String dirName, String fileName, String data) {
        try {
            Path workingDir = Paths.get("");
            if (dirName != null) {
                String pathString = workingDir.toAbsolutePath() + RESULTS_PATH + dirName;
                File file = new File(pathString, fileName + ".csv");
                new File(file.getParent()).mkdir();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append(data);
                fileWriter.close();
            }
            else {
                String pathString = workingDir.toAbsolutePath() + RESULTS_PATH;
                File file = new File(pathString, fileName + ".csv");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append(data);
                fileWriter.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static Result playGame(Player player1, Player player2) {
        Result res = new Result(player1, player2);

        for(int i = 0; i < TOTAL_ROUNDS; i++) {
            Decision p1 = player1.makeDecision();
            if (p1 == null) {
                p1 = Decision.DEFECT;
            }

            Decision p2 = player2.makeDecision();
            if (p2 == null) {
                p2 = Decision.DEFECT;
            }
            if(p1 == Decision.COOPERATE && p2 == Decision.COOPERATE) {
                player1.recordLastRound(p1, p2);
                player2.recordLastRound(p2, p1);
                res.updateResult(COOPERATIVE_WIN, COOPERATIVE_WIN, p1, p2);
            }
            else if(p1 == Decision.COOPERATE && p2 == Decision.DEFECT) {
                player1.recordLastRound(p1, p2);
                player2.recordLastRound( p2, p1);
                res.updateResult(ABSOLUTE_LOSS, ABSOLUTE_WIN, p1, p2);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.COOPERATE) {
                player1.recordLastRound(p1, p2);
                player2.recordLastRound(p2, p1);
                res.updateResult(ABSOLUTE_WIN, ABSOLUTE_LOSS, p1, p2);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.DEFECT) {
                player1.recordLastRound(p1, p2);
                player2.recordLastRound(p2, p1);
                res.updateResult(DEFECTIVE_WIN, DEFECTIVE_WIN, p1, p2);
            }
        }
        return res;
    }

    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private static List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static Player getInstance(String className) throws Exception {
        try {
            Class<?> c = Class.forName(className);
            Constructor<?> cons = c.getDeclaredConstructor();
            return (Player) cons.newInstance();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
