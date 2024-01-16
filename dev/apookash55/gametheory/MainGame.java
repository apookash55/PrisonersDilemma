package dev.apookash55.gametheory;

import dev.apookash55.gametheory.definition.Decision;
import dev.apookash55.gametheory.definition.Player;
import dev.apookash55.gametheory.definition.Result;

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
    private static final int TOTAL_ROUNDS = 1000000;
    private static final String PATH = "/dev/apookash55/gametheory/players";
    private static final String PACKAGE_PATH = "dev.apookash55.gametheory.players.";

    public static void main(String[] args) throws Exception {

        Path workingDir = Paths.get("");
        String pathString = workingDir.toAbsolutePath() + PATH;
        List<String> files = listFilesUsingJavaIO(pathString);

        Player[] players = new Player[files.size()];
        HashMap<Player, Integer> playersScores = new HashMap<>();
        int totalPlayers = files.size();

        for (int i = 0; i < totalPlayers; i++) {
            String className = PACKAGE_PATH + files.get(i).split("\\.")[0];
            players[i] = getInstance(className);
            playersScores.put(players[i], 0);
        }

        int count = 1;
        StringBuilder result = new StringBuilder("Player, Total Score\n");
        StringBuilder matches = new StringBuilder("Game, Player 1, Player 1 Score, Player 2, Player 2 Score\n");

        for(int i = 0; i < totalPlayers; i++) {
            for(int j = i + 1; j < totalPlayers; j++) {
                Result res = playGame(players[i], players[j]);

                playersScores.put(players[i], playersScores.get(players[i]) + res.getPlayer1());
                playersScores.put(players[j], playersScores.get(players[j]) + res.getPlayer2());

                String player1Name = files.get(i).split("\\.")[0];
                String player2Name = files.get(j).split("\\.")[0];
                matches.append(count).append(", ")
                        .append(player1Name).append(", ").append(res.getPlayer1()).append(", ")
                        .append(player2Name).append(", ").append(res.getPlayer2()).append("\n");
                writeToCSV("games", (count + "_" + player1Name + "VS" + player2Name), res.getAttempts());

                players[i].clearGame();
                players[j].clearGame();

                ++count;
            }
        }

        playersScores = sortByValue(playersScores);

        System.out.println("------ Final Score ------");
        for(Player player : playersScores.keySet()) {
            System.out.println(player.getClass().getSimpleName() + " : " + playersScores.get(player));
            result.append(player.getClass().getSimpleName()).append(", ").append(playersScores.get(player)).append("\n");
        }
        writeToCSV(null, "results", result.toString());
        writeToCSV(null, "games", matches.toString());
    }

    private static void writeToCSV(String dirName, String fileName, String data) {
        try {
            if (dirName != null) {
                File file = new File(dirName, fileName + ".csv");
                new File(file.getParent()).mkdir();
                FileWriter fileWriter = new FileWriter(dirName + "/" + fileName + ".csv");
                fileWriter.append(data);
                fileWriter.close();
            }
            else {
                FileWriter fileWriter = new FileWriter(fileName + ".csv");
                fileWriter.append(data);
                fileWriter.close();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Result playGame(Player player1, Player player2) {
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
                player1.recordAttempt(COOPERATIVE_WIN, p1, p2);
                player2.recordAttempt(COOPERATIVE_WIN, p2, p1);
            }
            else if(p1 == Decision.COOPERATE && p2 == Decision.DEFECT) {
                player1.recordAttempt(ABSOLUTE_LOSS, p1, p2);
                player2.recordAttempt(ABSOLUTE_WIN, p2, p1);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.COOPERATE) {
                player1.recordAttempt(ABSOLUTE_WIN, p1, p2);
                player2.recordAttempt(ABSOLUTE_LOSS, p2, p1);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.DEFECT) {
                player1.recordAttempt(DEFECTIVE_WIN, p1, p2);
                player2.recordAttempt(DEFECTIVE_WIN, p2, p1);
            }
        }
        String attempts = Player.generateResult(player1, player2);
        return new Result(player1.getScore(), player2.getScore(), attempts);
    }

    private static HashMap<Player, Integer> sortByValue(HashMap<Player, Integer> hm)
    {
        List<Map.Entry<Player, Integer> > list =
                new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        HashMap<Player, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Player, Integer> aa : list) {
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
