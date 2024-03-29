import java.util.ArrayList;

public class Players {

    private final ArrayList<Player> players;

    public  Players(){
        this.players = new ArrayList<>();
    }

    public Player bestTotalWorth(Player playerCurrent){
        Player player = null;
        int temporary;
        int totalWorth = 0;
        for (int i = 0; i < size(); i++) {
            if (!playerCurrent.equals(getPlayer(i))){
                temporary = 0;
                temporary += getPlayer(i).getMoney();
                for (int j = 0; j < getPlayer(i).sizeProperties(); j++) {
                    temporary += getPlayer(i).getProperties().getProperty(j).getPrice();
                }
                if (player == null){
                    totalWorth = temporary;
                    player = getPlayer(i);
                } else if (totalWorth < temporary){
                    totalWorth = temporary;
                    player = getPlayer(i);
                }
            }
        }
        return player;
    }

    public Player worstTotalWorth(Player playerCurrent){
        Player player = null;
        int temporary;
        int totalWorth = 10000000;
        for (int i = 0; i < size(); i++) {
            if (!playerCurrent.equals(getPlayer(i))){
                temporary = 0;
                temporary += getPlayer(i).getMoney();
                for (int j = 0; j < getPlayer(i).sizeProperties(); j++) {
                    temporary += getPlayer(i).getProperties().getProperty(j).getPrice();
                }
                if (player == null){
                    totalWorth = temporary;
                    player = getPlayer(i);
                } else if (totalWorth > temporary){
                    totalWorth = temporary;
                    player = getPlayer(i);
                }
            }
        }
        return player;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public int size(){
        return players.size();
    }

    public boolean isFinish(){
        boolean bool = false;
        for (Player player : players) {
            if (player.getMoney() <= 0 && player.sizeProperties() == 0) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void setMarathon(){
        for (Player player : players){
            player.setMarathon();
        }
    }

    public String winner(){
        int temporary;
        int money = 0;
        String winner = "";
        for (int i = 0; i < size(); i++) {
            temporary = getPlayer(i).getMoney();
            for (int j = 0; j < getPlayer(i).sizeProperties(); j++) {
                temporary += getPlayer(i).getProperties().getProperty(j).getPrice();
            }
            System.out.println(players.get(i).getName() + ": Money- " + getPlayer(i).getMoney() + " Properties- " + getPlayer(i).getProperties().size());
            if (temporary > money){
                winner = players.get(i).getName();
                money = temporary;
            }
        }
        return winner;
    }
}
