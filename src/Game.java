import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game {

    public static void main(String[]args){
        int times = 100000;
        HashMap<String, Integer> counter = new HashMap<>();
        ArrayList<String> players = new ArrayList<>();
        players.add("player1");
        players.add("player2");
        players.add("player3");
        for (String s : players) {
            counter.put(s, 0);
        }
        for (int i = 0; i < times; i++) {
            Board board = new Board(i);
            Collections.shuffle(players);
            for (String player : players) {
                switch (player) {
                    case "player1":
                        board.addPlayer(new PlayerTypeOne("player1", 0, 1500, new Properties(), false, board.getDice()));
                        break;
                    case "player2":
                        board.addPlayer(new PlayerTypeTwo("player2", 0, 1500, new Properties(), false, board.getDice()));
                        break;
                    case "player3":
                        board.addPlayer(new PlayerTypeThree("player3", 0, 1500, new Properties(), false, board.getDice()));
                        break;
                }
            }
            while (!board.isFinish()){
                board.play();
                board.changeTurn();
            }
            System.out.println("Game " + i + "#:");
            int winnerIndex = board.winner();
            counter.put(players.get(winnerIndex), counter.get(players.get(winnerIndex)) + 1);
        }
        System.out.println(counter);
    }
}
