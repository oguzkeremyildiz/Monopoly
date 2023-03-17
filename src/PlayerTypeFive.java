import java.util.Random;

public class PlayerTypeFive extends Player{

    public PlayerTypeFive(String name, int place, int money, Properties properties, boolean jail, Random dice) {
        super(name, place, money, properties, jail, dice);
    }

    @Override
    public void play(Properties properties, Players players, Jail jail, Locations locations, CardLocations cardLocations, Cards cards) {}
}
