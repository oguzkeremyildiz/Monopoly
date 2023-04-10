import java.util.*;

public class Board {
    private final Properties properties;
    private final Jail jail;
    private final Locations locations;
    private final CardLocations cardLocations;
    private final Cards cards;
    private final Players players;
    private int turn;
    private final Random dice;

    private void addProperties(){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 70);
        map.put(2, 130);
        map.put(3, 220);
        map.put(4, 370);
        map.put(5, 750);
        properties.addProperty(new Property(1, 60,(HashMap<Integer, Integer>) map.clone() , 0, false));
        map.clear();
        map.put(1, 70);
        map.put(2, 130);
        map.put(3, 220);
        map.put(4, 370);
        map.put(5, 750);
        properties.addProperty(new Property(3, 60, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 80);
        map.put(2, 140);
        map.put(3, 240);
        map.put(4, 410);
        map.put(5, 800);
        properties.addProperty(new Property(5, 100, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 80);
        map.put(2, 140);
        map.put(3, 240);
        map.put(4, 410);
        map.put(5, 800);
        properties.addProperty(new Property(6, 100, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 100);
        map.put(2, 160);
        map.put(3, 260);
        map.put(4, 440);
        map.put(5, 860);
        properties.addProperty(new Property(8, 120, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 110);
        map.put(2, 180);
        map.put(3, 290);
        map.put(4, 460);
        map.put(5, 900);
        properties.addProperty(new Property(10, 140, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 110);
        map.put(2, 180);
        map.put(3, 290);
        map.put(4, 460);
        map.put(5, 900);
        properties.addProperty(new Property(12, 140, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 130);
        map.put(2, 200);
        map.put(3, 310);
        map.put(4, 490);
        map.put(5, 980);
        properties.addProperty(new Property(13, 160, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 140);
        map.put(2, 210);
        map.put(3, 330);
        map.put(4, 520);
        map.put(5, 1000);
        properties.addProperty(new Property(14, 180, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 140);
        map.put(2, 210);
        map.put(3, 330);
        map.put(4, 520);
        map.put(5, 1000);
        properties.addProperty(new Property(15, 180, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 160);
        map.put(2, 230);
        map.put(3, 350);
        map.put(4, 550);
        map.put(5, 1100);
        properties.addProperty(new Property(17, 200, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 170);
        map.put(2, 250);
        map.put(3, 380);
        map.put(4, 580);
        map.put(5, 1160);
        properties.addProperty(new Property(19, 220, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 170);
        map.put(2, 250);
        map.put(3, 380);
        map.put(4, 580);
        map.put(5, 1160);
        properties.addProperty(new Property(21, 220, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 190);
        map.put(2, 270);
        map.put(3, 400);
        map.put(4, 610);
        map.put(5, 1200);
        properties.addProperty(new Property(22, 240, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 200);
        map.put(2, 280);
        map.put(3, 420);
        map.put(4, 640);
        map.put(5, 1300);
        properties.addProperty(new Property(23, 260, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 200);
        map.put(2, 280);
        map.put(3, 420);
        map.put(4, 640);
        map.put(5, 1300);
        properties.addProperty(new Property(24, 260, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 220);
        map.put(2, 300);
        map.put(3, 440);
        map.put(4, 670);
        map.put(5, 1340);
        properties.addProperty(new Property(26, 280, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 230);
        map.put(2, 320);
        map.put(3, 460);
        map.put(4, 700);
        map.put(5, 1400);
        properties.addProperty(new Property(28, 300, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 230);
        map.put(2, 320);
        map.put(3, 460);
        map.put(4, 700);
        map.put(5, 1400);
        properties.addProperty(new Property(30, 300, (HashMap<Integer, Integer>) map.clone() , 0, false));
        map.clear();
        map.put(1, 250);
        map.put(2, 340);
        map.put(3, 480);
        map.put(4, 730);
        map.put(5, 1440);
        properties.addProperty(new Property(31, 320, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 270);
        map.put(2, 360);
        map.put(3, 510);
        map.put(4, 740);
        map.put(5, 1500);
        properties.addProperty(new Property(33, 350, (HashMap<Integer, Integer>) map.clone(), 0, false));
        map.clear();
        map.put(1, 300);
        map.put(2, 400);
        map.put(3, 560);
        map.put(4, 810);
        map.put(5, 1600);
        properties.addProperty(new Property(35, 400, (HashMap<Integer, Integer>) map.clone(), 0, false));
    }

    public void addPlayer(Player player){
        players.addPlayer(player);
    }

    public boolean isFinish(){
        return players.isFinish();
    }

    public String winner(){
        return players.winner();
    }

    public void changeTurn(){
        turn = (turn + 1) % players.size();
    }

    public void play(){
        players.getPlayer(turn).play(properties, players, jail, locations, cardLocations, cards);
    }

    public Random getDice(){
        return dice;
    }

    public Board(int randomNo){
        properties = new Properties();
        addProperties();
        jail = new Jail(27);
        locations = new Locations();
        locations.addLocation(new Location(7));
        locations.addLocation(new Location(16));
        locations.addLocation(new Location(25));
        locations.addLocation(new Location(34));
        cardLocations = new CardLocations();
        cardLocations.addCardLocation(new CardLocation(2));
        cardLocations.addCardLocation(new CardLocation(4));
        cardLocations.addCardLocation(new CardLocation(11));
        cardLocations.addCardLocation(new CardLocation(20));
        cardLocations.addCardLocation(new CardLocation(29));
        cardLocations.addCardLocation(new CardLocation(32));
        cards = new Cards();
        cards.addCard(new Card(0, "TRAFİK ARAPSAÇI"));
        cards.addCard(new Card(1, "YOL VERGİSİ"));
        cards.addCard(new Card(2, "EVİM ŞAHANE"));
        cards.addCard(new Card(3, "FIRTINA BÖLGESİ"));
        cards.addCard(new Card(4, "BİR TİTREME GELDİ"));
        cards.addCard(new Card(5, "YIKIM KARARI"));
        cards.addCard(new Card(6, "MARATON ZAMANI"));
        cards.addCard(new Card(7, "SENİ YAKALADIM"));
        cards.addCard(new Card(8, "KARABAŞ'IN İNTİKAMI"));
        cards.addCard(new Card(9, "HAVADA AŞK KOKUSU VAR"));
        cards.addCard(new Card(10, "BASKIYI DURDURUN"));
        cards.addCard(new Card(11, "PRİM ZAMANI"));
        cards.addCard(new Card(12, "SUÇLU YAKALANDI"));
        cards.addCard(new Card(13, "KOMŞUM BİR ÜNLÜ"));
        cards.addCard(new Card(14, "NE TURDU AMA!"));
        cards.addCard(new Card(15, "PLAN DEĞİŞİKLİĞİ"));
        cards.addCard(new Card(16, "HAFTANIN TEKLİFİ"));
        cards.addCard(new Card(17, "PERİLİ EV"));
        cards.addCard(new Card(18, "PARA İÇİNDE YÜZÜYORSUN"));
        cards.addCard(new Card(19, "BEBEĞİN OLDU!"));
        cards.addCard(new Card(20, "EV PARTİSİ"));
        cards.addCard(new Card(21, "OFF! BU KOKU DA NE?"));
        cards.addCard(new Card(22, "HASTALIK MEVSİMİ"));
        dice = new Random(randomNo);
        cards.shuffle(dice);
        players = new Players();
        turn = 0;
    }
}
