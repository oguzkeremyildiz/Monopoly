import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private static void addPlayer(String type, Board board) {
        switch (type) {
            case "player1":
                board.addPlayer(new PlayerTypeOne("player1", 0, 1500, new Properties(), false, board.getDice()));
                break;
            case "player2":
                board.addPlayer(new PlayerTypeTwo("player2", 0, 1500, new Properties(), false, board.getDice()));
                break;
            case "player3":
                board.addPlayer(new PlayerTypeThree("player3", 0, 1500, new Properties(), false, board.getDice()));
                break;
            case "player4":
                board.addPlayer(new PlayerTypeFour("player4", 0, 1500, new Properties(), false, board.getDice()));
                break;
            case "player5":
                board.addPlayer(new PlayerTypeFive("player5", 0, 1500, new Properties(), false, board.getDice()));
                break;
            case "player6":
                board.addPlayer(new PlayerTypeSix("player6", 0, 1500, new Properties(), false, board.getDice()));
                break;
        }
    }

    private static void printWinPercentage(HashMap<String, Integer> counter, int times) {
        for (String key : counter.keySet()) {
            System.out.println(key + ": %" + ((100 * counter.get(key) + 0.00) / times));
        }
    }

    public static void main(String[]args){
        int times = 1000000;
        HashMap<String, Integer> counter = new HashMap<>();
        ArrayList<String> players = new ArrayList<>();
        players.add("player1");
        players.add("player2");
        players.add("player3");
        players.add("player4");
        players.add("player5");
        players.add("player6");
        for (String s : players) {
            counter.put(s, 0);
        }
        for (int i = 0; i < times; i++) {
            Board board = new Board(i);
            Collections.shuffle(players, new Random(i));
            for (String player : players) {
                addPlayer(player, board);
            }
            while (!board.isFinish()){
                board.play();
                board.changeTurn();
            }
            System.out.println("Game " + i + "#:");
            String winner = board.winner();
            counter.put(winner, counter.get(winner) + 1);
        }
        System.out.println(counter);
        printWinPercentage(counter, times);
    }
}
