import java.util.Random;

public class PlayerTypeOne extends Player{

    public PlayerTypeOne(String name, int place, int money, Properties properties, boolean jail, Random dice){
        super(name, place, money, properties, jail, dice);
    }

    protected void changeProperty(Players players, Properties allProperties){
        if (properties.size() > 0){
            for (int i = 0; i < allProperties.size(); i++) {
                Property propertyToCheck = allProperties.getProperty(allProperties.size() - i - 1);
                if (!containsProperty(propertyToCheck)){
                    if (propertyToCheck.isStatus()){
                        for (int j = 0; j < players.size(); j++) {
                            if (players.getPlayer(j).containsProperty(propertyToCheck)){
                                Property tmp = properties.getProperty(0);
                                removeProperty(0);
                                players.getPlayer(j).addOnlyProperty(tmp);
                                tmp = propertyToCheck;
                                int index = players.getPlayer(j).findIndex(tmp);
                                players.getPlayer(j).removeProperty(index);
                                addOnlyProperty(tmp);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    protected void goOrIncreaseProperty(Properties allProperties){
        Property property = containsFalseStatus(allProperties);
        if (property != null){
            if (property.getPrice() < getMoney()){
                addProperty(property);
                setPlace(property.getTableNo());
            } else if (getProperties().size() > 0){
                property = canIncrease();
                if (property != null){
                    property.setKey(property.getKey() + 1);
                }
            }
        } else if (getProperties().size() > 0){
            property = canIncrease();
            if (property != null){
                property.setKey(property.getKey() + 1);
            }
        }
    }

    protected void sellProperty(){
        if (sizeProperties() > 0){
            if (getMoney() < 0){
                int smallestValue = 5000;
                Property property = null;
                int index = 0;
                for (int i = 0; i < sizeProperties(); i++) {
                    if (getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey()) < smallestValue){
                        smallestValue = getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey());
                        property = getProperties().getProperty(i);
                        index = i;
                    }
                }
                setMoney(getMoney() + property.getPrice());
                getProperties().getProperty(index).setKey(0);
                getProperties().getProperty(index).setStatus(false);
                getProperties().removeProperty(index);
                if (sizeProperties() > 0){
                    if (getMoney() < 0){
                        sellProperty();
                    }
                }
            }
        }
    }

    protected void giveProperty(Player player2){
        if (getProperties().size() > 0){
            if (getMoney() < 0){
                int smallestValue = 5000;
                Property property = null;
                int index = 0;
                for (int i = 0; i < sizeProperties(); i++) {
                    if (getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey()) < smallestValue){
                        smallestValue = getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey());
                        property = getProperties().getProperty(i);
                        index = i;
                    }
                }
                setMoney(getMoney() + getProperties().getProperty(index).getPrice());
                player2.getProperties().addProperty(property);
                getProperties().removeProperty(index);
                if (sizeProperties() > 0){
                    if (getMoney() < 0){
                        giveProperty(player2);
                    }
                }
            }
        }
    }

    protected void increaseProperty(Players players, Property property){
        int pay;
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.getPlayer(i).getProperties().size(); j++) {
                if (players.getPlayer(i).getProperties().getProperty(j).equals(property)){
                    if (players.getPlayer(i).equals(this)){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                    } else {
                        if (marathonMode){
                            if (getMoney() - property.getPropertyValue().get(1) < 0){
                                pay = getMoney();
                            } else {
                                pay = property.getPropertyValue().get(1);
                            }
                            setMoney(getMoney() - property.getPropertyValue().get(1));
                            players.getPlayer(i).setMoney(players.getPlayer(i).getMoney() + pay);
                            if (property.getKey() < 5){
                                property.setKey(property.getKey() + 1);
                            }
                            if (getMoney() < 0){
                                if (sizeProperties() > 0){
                                    giveProperty(players.getPlayer(i));
                                }
                            }
                            paymentsInMarathon++;
                            if (paymentsInMarathon == 2){
                                marathonMode = false;
                                paymentsInMarathon = 0;
                            }
                        } else {
                            if (getMoney() - property.getPropertyValue().get(property.getKey()) < 0){
                                pay = getMoney();
                            } else {
                                pay = property.getPropertyValue().get(property.getKey());
                            }
                            setMoney(getMoney() - property.getPropertyValue().get(property.getKey()));
                            players.getPlayer(i).setMoney(players.getPlayer(i).getMoney() + pay);
                            if (property.getKey() < 5){
                                property.setKey(property.getKey() + 1);
                            }
                            if (getMoney() < 0){
                                if (sizeProperties() > 0){
                                    giveProperty(players.getPlayer(i));
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    protected void chooseBiggestValue(){
        if (getProperties().size() > 0){
            Property property = null;
            int biggest = 0;
            for (int i = 0; i < getProperties().size(); i++) {
                if (getProperties().getProperty(i).getKey() < 5){
                    if (getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey()) > biggest){
                        property = getProperties().getProperty(i);
                        biggest = getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey());
                    }
                }
            }
            if (property != null){
                property.setKey(5);
            }
        }
    }

    protected void chooseSmallestValue(){
        if (getProperties().size() > 0){
            if (!containsKeyValueOne()){
                Property property = null;
                int smallest = 5000;
                for (int i = 0; i < getProperties().size(); i++) {
                    if (getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey()) < smallest){
                        property = getProperties().getProperty(i);
                        smallest = getProperties().getProperty(i).getPropertyValue().get(getProperties().getProperty(i).getKey());
                    }
                }
                if (property != null){
                    property.setKey(1);
                }
            }
        }
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
                for (int t = 0; t < allProperties.size(); t++) {
                    if (allProperties.getProperty(t).getTableNo() == getPlace()) {
                        if (!allProperties.getProperty(t).isStatus()){
                            if (getMoney() > allProperties.getProperty(t).getPrice()){
                                addProperty(allProperties.getProperty(t));
                                setKeysAllUp(allProperties.getProperty(t), allProperties);
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
