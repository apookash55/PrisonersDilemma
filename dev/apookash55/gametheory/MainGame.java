package dev.apookash55.gametheory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainGame {

    public static void main(String[] args) throws Exception{
        String path = "/dev/apookash55/gametheory/players";
        String pack = "dev.apoorv.gametheory.players.";

        Path workingDir = Paths.get("");
        String pathString = workingDir.toAbsolutePath().toString() + path;
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
        for(int i = 0; i < totalPlayers; i++) {
            for(int j = i + 1; j < totalPlayers; j++) {
                System.out.println("------ Match " + count++ + " ------");
                Result res = playGame(players[i], players[j]);
                playersScores.put(players[i], playersScores.get(players[i]) + res.getPlayer1());
                playersScores.put(players[j], playersScores.get(players[j]) + res.getPlayer2());
                System.out.println(players[i].getClass().getSimpleName() + " : " + res.getPlayer1() +
                        "\n" + players[j].getClass().getSimpleName() + " : " + res.getPlayer2());
            }
        }

        playersScores = sortByValue(playersScores);

        System.out.println("------ Final Score ------");
        for(Player player : playersScores.keySet()) {
            System.out.println("Player " + player.getClass().getSimpleName() + " : " + playersScores.get(player));
        }
    }

    public static HashMap<Player, Integer> sortByValue(HashMap<Player, Integer> hm)
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
            char p1 = player1.makeDecision();
            char p2 = player2.makeDecision();
            if(p1 == 'C' && p2 == 'C') {
                player1.recordAttempt(3, p1, p2);
                player2.recordAttempt(3, p2, p1);
            }
            else if(p1 == 'C' && p2 == 'D') {
                player1.recordAttempt(0, p1, p2);
                player2.recordAttempt(5, p2, p1);
            }
            else if(p1 == 'D' && p2 == 'C') {
                player1.recordAttempt(5, p1, p2);
                player2.recordAttempt(0, p2, p1);
            }
            else if(p1 == 'D' && p2 == 'D') {
                player1.recordAttempt(1, p1, p2);
                player2.recordAttempt(1, p2, p1);
            }
        }
        System.out.println(Player.getAttempts(player1, player2));
        Result res =  new Result(player1.getScore(), player2.getScore());
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
