import java.util.ArrayList;

public class CardLocations {

    private final ArrayList<CardLocation> cardLocations;

    public CardLocations(){
        this.cardLocations = new ArrayList<>();
    }

    public void addCardLocation(CardLocation cardLocation){
        cardLocations.add(cardLocation);
    }

    public boolean containsTableNo(int place){
        boolean bool = false;
        for (CardLocation location : cardLocations) {
            if (location.getTableNo() == place) {
                bool = true;
                break;
            }
        }
        return bool;
    }
}
