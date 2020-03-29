import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cards {

    private ArrayList<Card> cards = new ArrayList<>();

    public Cards(){
    }

    public void shuffle(Random dice){
        Collections.shuffle(cards, dice);
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card getCard(){
        Card temporary = cards.get(0);
        cards.remove(0);
        cards.add(temporary);
        return temporary;
    }
}
