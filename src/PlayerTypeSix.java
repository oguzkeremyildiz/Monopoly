import java.util.Random;

public class PlayerTypeSix extends Player{

    public PlayerTypeSix(String name, int place, int money, Properties properties, boolean jail, Random dice) {
        super(name, place, money, properties, jail, dice);
    }

    @Override
    public void play(Properties allProperties, Players players, Jail jail, Locations locations, CardLocations cardLocations, Cards cards) {
        if (!isJail()){
            int totalDice = dice() + dice();
            if (getPlace() + totalDice < 36){
                setPlace(getPlace() + totalDice);
            } else {
                setMoney(getMoney() + 200);
                setPlace(getPlace() + totalDice - 36);
            }
            if (allProperties.containsTableNo(getPlace())){
                for (int t = 0; t < allProperties.size(); t++) {
                    if (allProperties.getProperty(t).getTableNo() == getPlace()) {
                        if (!allProperties.getProperty(t).isStatus()){
                            if (getMoney() > allProperties.getProperty(t).getPrice()){
                                if (totalDice > 6) {
                                    addProperty(allProperties.getProperty(t));
                                    setKeysAllUp(allProperties.getProperty(t), allProperties);
                                }
                            }
                        } else {
                            increaseProperty(players, allProperties.getProperty(t));
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
