public class Game {
    private static void percentage(int[] percentages, int times){
        double divide = (100.0 / times);
        for (int i = 0; i < percentages.length; i++){
            System.out.println("player" + (i + 1) + "percentage: " + "%" + percentages[i] * divide);
        }
    }

    public static void main(String[]args){
        int[] percentages = new int[3];
        int times = 100000;
        for (int i = 0; i < times; i++) {
            Board board = new Board(i);
            board.addPlayer(new PlayerTypeOne("player1",0, 1500, new Properties(), false, board.getDice()));
            board.addPlayer(new PlayerTypeThree("player2",0, 1500, new Properties(), false, board.getDice()));
            board.addPlayer(new PlayerTypeTwo("player3",0, 1500, new Properties(), false, board.getDice()));
            while (!board.isFinish()){
                board.play();
                board.changeTurn();
            }
            percentages[board.winner()]++;
        }
        percentage(percentages, times);
    }
}
