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

    public static void main(String[] args) throws Exception{
        String path = "/dev/apookash55/gametheory/players";
        String pack = "dev.apookash55.gametheory.players.";

        Path workingDir = Paths.get("");
        String pathString = workingDir.toAbsolutePath() + path;
        List<String> files = listFilesUsingJavaIO(pathString);

        Player[] players = new Player[files.size()];
        HashMap<Player, Integer> playersScores = new HashMap<>();
        int totalPlayers = files.size();

        for (int i = 0; i < totalPlayers; i++) {
            String className = pack + files.get(i).split("\\.")[0];
            Class<?> c = Class.forName(className);
            Constructor<?> cons = c.getDeclaredConstructor(int.class);
            players[i] = (Player) cons.newInstance(200);
            playersScores.put(players[i], 0);
        }

        int count = 1;
        StringBuilder result = new StringBuilder("Player, Total Score\n");
        StringBuilder matches = new StringBuilder("Match, Player 1, Player 1 Score, Player 2, Player 2 Score\n");

        for(int i = 0; i < totalPlayers; i++) {
            for(int j = i + 1; j < totalPlayers; j++) {
                Result res = playGame(players[i], players[j]);
                playersScores.put(players[i], playersScores.get(players[i]) + res.getPlayer1());
                playersScores.put(players[j], playersScores.get(players[j]) + res.getPlayer2());
                String player1Name = players[i].getClass().getSimpleName();
                String player2Name = players[j].getClass().getSimpleName();
                matches.append(count).append(", ")
                        .append(player1Name).append(", ").append(res.getPlayer1()).append(", ")
                        .append(player2Name).append(", ").append(res.getPlayer2()).append("\n");
                writeToCSV("matches", (count + "_" + player1Name + "VS" + player2Name), res.getAttempts());
                ++count;
            }
        }

        playersScores = sortByValue(playersScores);

        for(Player player : playersScores.keySet()) {
            result.append(player.getClass().getSimpleName()).append(", ").append(playersScores.get(player)).append("\n");
        }
        writeToCSV(null, "results", result.toString());
        writeToCSV(null, "matches", matches.toString());
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

    private static Result playGame(Player player1, Player player2) {
        for(int i = 0; i < 200; i++) {
            Decision p1 = player1.makeDecision();
            Decision p2 = player2.makeDecision();
            if(p1 == Decision.COOPERATE && p2 == Decision.COOPERATE) {
                player1.recordAttempt(3, p1, p2);
                player2.recordAttempt(3, p2, p1);
            }
            else if(p1 == Decision.COOPERATE && p2 == Decision.DEFECT) {
                player1.recordAttempt(0, p1, p2);
                player2.recordAttempt(5, p2, p1);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.COOPERATE) {
                player1.recordAttempt(5, p1, p2);
                player2.recordAttempt(0, p2, p1);
            }
            else if(p1 == Decision.DEFECT && p2 == Decision.DEFECT) {
                player1.recordAttempt(1, p1, p2);
                player2.recordAttempt(1, p2, p1);
            }
        }
        String attempts = Player.getAttempts(player1, player2);
        Result res =  new Result(player1.getScore(), player2.getScore(), attempts);
        player1.clearAttempt();
        player2.clearAttempt();
        return res;
    }

    private static List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
