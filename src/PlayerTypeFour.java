import java.util.Random;

public class PlayerTypeFour extends Player{

    // the player who didn't buy anywhere

    public PlayerTypeFour(String name, int place, int money, Properties properties, boolean jail, Random dice) {
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
            int place = getPlace();
            Property property = allProperties.containsTableNo(place);
            if (property != null){
                if (property.isStatus()){
                    increaseProperty(players, property);
                }
            } else if (jail.containsTableNo(place)){
                setJail(true);
                setPlace(9);
            } else if (locations.containsTableNo(place)){
                setMoney(getMoney() - 100);
                if (getMoney() < 0){
                    sellProperty();
                }
            } else if (cardLocations.containsTableNo(place)){
                playCard(cards.getCard(), players, allProperties);
            }
        } else {
            toursInJail++;
            jailType();
        }
    }
}
