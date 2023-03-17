import java.util.Random;

public class PlayerTypeThree extends Player{

    public PlayerTypeThree(String name, int place, int money, Properties properties, boolean jail, Random dice){
        super(name, place, money, properties, jail, dice);
    }

    public void play(Properties allProperties, Players players, Jail jail, Locations locations, CardLocations cardLocations, Cards cards){
        if (!isJail()){
            int totalDice = dice() + dice();
            if (getPlace() + totalDice < 36){
                setPlace(getPlace() + totalDice);
            } else {
                setMoney(getMoney() + 200);
                setPlace(getPlace() + totalDice - 36);
            }
            if (allProperties.containsTableNo(getPlace())){
                for (int j = 0; j < allProperties.size(); j++) {
                    if (allProperties.getProperty(j).getTableNo() == getPlace()) {
                        if (!allProperties.getProperty(j).isStatus()){
                            if (getMoney() / 5.00 > allProperties.getProperty(j).getPrice()){
                                addProperty(allProperties.getProperty(j));
                                setKeysAllUp(allProperties.getProperty(j), allProperties);
                            }
                        } else {
                            increaseProperty(players, allProperties.getProperty(j));
                        }
                        break;
                    }
                }
            } else if (jail.containsTableNo(getPlace())){
                setJail(true);
                setPlace(9);
            } else if (locations.containsTableNo(getPlace())){
                setMoney(getMoney() - 100);
                if (getMoney() < 0){
                    sellProperty();
                }
            } else if (cardLocations.containsTableNo(getPlace())){
                playCard(cards.getCard(), players, allProperties);
            }
        } else {
            toursInJail++;
            jailType();
        }
    }
}
