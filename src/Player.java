import java.util.Random;

public abstract class Player {

    private int place;
    private int money;
    private final Properties properties;
    private final String name;
    private boolean jail;
    protected int toursInJail = 0;
    private boolean marathonMode = false;
    private int paymentsInMarathon = 0;
    private final Random dice;

    private void changeProperty(Players players, Properties allProperties){
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

    private void goOrIncreaseProperty(Properties allProperties){
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

    private void chooseBiggestValue(){
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

    private void chooseSmallestValue(){
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
    
    public abstract void play(Properties properties, Players players, Jail jail, Locations locations, CardLocations cardLocations, Cards cards);

    public Player(String name, int place, int money, Properties properties, boolean jail, Random dice){
        this.place = place;
        this.money = money;
        this.properties = properties;
        this.name = name;
        this.jail = jail;
        this.dice = dice;
    }

    protected int dice(){
        int number = dice.nextInt(6);
        return number + 1;
    }

    protected boolean containsProperty(Property property){
        return properties.containsProperty(property);
    }

    protected void removeProperty(int index){
        properties.removeProperty(index);
    }

    public void setKeysAllUp(Property property, Properties properties){
        switch (property.getTableNo()){
            case 1:
                if (properties.getProperty(1).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(1).getKey() < 5){
                        properties.getProperty(1).setKey(properties.getProperty(1).getKey() + 1);
                    }
                }
                break;
            case 3:
                if (properties.getProperty(0).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(0).getKey() < 5){
                        properties.getProperty(0).setKey(properties.getProperty(0).getKey() + 1);
                    }
                }
                break;
            case 5:
                if (properties.getProperty(3).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(3).getKey() < 5){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() + 1);
                        }
                        if (properties.getProperty(4).getKey() < 5){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() + 1);
                        }
                    }
                }
                break;
            case 6:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(2).getKey() < 5){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() + 1);
                        }
                        if (properties.getProperty(4).getKey() < 5){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() + 1);
                        }
                    }
                }
                break;
            case 8:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(3).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(2).getKey() < 5){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() + 1);
                        }
                        if (properties.getProperty(3).getKey() < 5){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() + 1);
                        }
                    }
                }
                break;
            case 10:
                if (properties.getProperty(6).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(6).getKey() < 5){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() + 1);
                        }
                        if (properties.getProperty(7).getKey() < 5){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() + 1);
                        }
                    }
                }
                break;
            case 12:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(5).getKey() < 5){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() + 1);
                        }
                        if (properties.getProperty(7).getKey() < 5){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() + 1);
                        }
                    }
                }
                break;
            case 13:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(6).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(5).getKey() < 5){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() + 1);
                        }
                        if (properties.getProperty(6).getKey() < 5){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() + 1);
                        }
                    }
                }
                break;
            case 14:
                if (properties.getProperty(9).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(9).getKey() < 5){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() + 1);
                        }
                        if (properties.getProperty(10).getKey() < 5){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() + 1);
                        }
                    }
                }
                break;
            case 15:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(8).getKey() < 5){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() + 1);
                        }
                        if (properties.getProperty(10).getKey() < 5){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() + 1);
                        }
                    }
                }
                break;
            case 17:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(9).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(8).getKey() < 5){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() + 1);
                        }
                        if (properties.getProperty(9).getKey() < 5){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() + 1);
                        }
                    }
                }
                break;
            case 19:
                if (properties.getProperty(12).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(12).getKey() < 5){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() + 1);
                        }
                        if (properties.getProperty(13).getKey() < 5){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() + 1);
                        }
                    }
                }
                break;
            case 21:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(11).getKey() < 5){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() + 1);
                        }
                        if (properties.getProperty(13).getKey() < 5){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() + 1);
                        }
                    }
                }
                break;
            case 22:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(12).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(11).getKey() < 5){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() + 1);
                        }
                        if (properties.getProperty(12).getKey() < 5){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() + 1);
                        }
                    }
                }
                break;
            case 23:
                if (properties.getProperty(15).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(15).getKey() < 5){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() + 1);
                        }
                        if (properties.getProperty(16).getKey() < 5){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() + 1);
                        }
                    }
                }
                break;
            case 24:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(14).getKey() < 5){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() + 1);
                        }
                        if (properties.getProperty(16).getKey() < 5){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() + 1);
                        }
                    }
                }
                break;
            case 26:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(15).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(14).getKey() < 5){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() + 1);
                        }
                        if (properties.getProperty(15).getKey() < 5){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() + 1);
                        }
                    }
                }
                break;
            case 28:
                if (properties.getProperty(18).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(18).getKey() < 5){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() + 1);
                        }
                        if (properties.getProperty(19).getKey() < 5){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() + 1);
                        }
                    }
                }
                break;
            case 30:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(17).getKey() < 5){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() + 1);
                        }
                        if (properties.getProperty(19).getKey() < 5){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() + 1);
                        }
                    }
                }
                break;
            case 31:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(18).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(17).getKey() < 5){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() + 1);
                        }
                        if (properties.getProperty(18).getKey() < 5){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() + 1);
                        }
                    }
                }
                break;
            case 33:
                if (properties.getProperty(21).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(21).getKey() < 5){
                        properties.getProperty(21).setKey(properties.getProperty(21).getKey() + 1);
                    }
                }
                break;
            case 35:
                if (properties.getProperty(20).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(20).getKey() < 5){
                        properties.getProperty(20).setKey(properties.getProperty(20).getKey() + 1);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void setKeysAllDown(Property property, Properties properties){
        switch (property.getTableNo()){
            case 1:
                if (properties.getProperty(1).isStatus()){
                    if (property.getKey() > 1){
                        property.setKey(property.getKey() - 1);
                    }
                    if (properties.getProperty(1).getKey() > 1){
                        properties.getProperty(1).setKey(properties.getProperty(1).getKey() - 1);
                    }
                }
                break;
            case 3:
                if (properties.getProperty(0).isStatus()){
                    if (property.getKey() > 1){
                        property.setKey(property.getKey() - 1);
                    }
                    if (properties.getProperty(0).getKey() > 1){
                        properties.getProperty(0).setKey(properties.getProperty(0).getKey() - 1);
                    }
                }
                break;
            case 5:
                if (properties.getProperty(3).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(3).getKey() > 1){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() - 1);
                        }
                        if (properties.getProperty(4).getKey() > 1){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() - 1);
                        }
                    }
                }
                break;
            case 6:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(2).getKey() > 1){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() - 1);
                        }
                        if (properties.getProperty(4).getKey() > 1){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() - 1);
                        }
                    }
                }
                break;
            case 8:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(3).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(2).getKey() > 1){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() - 1);
                        }
                        if (properties.getProperty(3).getKey() > 1){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() - 1);
                        }
                    }
                }
                break;
            case 10:
                if (properties.getProperty(6).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(6).getKey() > 1){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() - 1);
                        }
                        if (properties.getProperty(7).getKey() > 1){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() - 1);
                        }
                    }
                }
                break;
            case 12:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(5).getKey() > 1){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() - 1);
                        }
                        if (properties.getProperty(7).getKey() > 1){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() - 1);
                        }
                    }
                }
                break;
            case 13:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(6).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(5).getKey() > 1){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() - 1);
                        }
                        if (properties.getProperty(6).getKey() > 1){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() - 1);
                        }
                    }
                }
                break;
            case 14:
                if (properties.getProperty(9).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(9).getKey() > 1){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() - 1);
                        }
                        if (properties.getProperty(10).getKey() > 1){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() - 1);
                        }
                    }
                }
                break;
            case 15:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(8).getKey() > 1){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() - 1);
                        }
                        if (properties.getProperty(10).getKey() > 1){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() - 1);
                        }
                    }
                }
                break;
            case 17:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(9).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(8).getKey() > 1){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() - 1);
                        }
                        if (properties.getProperty(9).getKey() > 1){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() - 1);
                        }
                    }
                }
                break;
            case 19:
                if (properties.getProperty(12).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(12).getKey() > 1){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() - 1);
                        }
                        if (properties.getProperty(13).getKey() > 1){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() - 1);
                        }
                    }
                }
                break;
            case 21:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(11).getKey() > 1){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() - 1);
                        }
                        if (properties.getProperty(13).getKey() > 1){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() - 1);
                        }
                    }
                }
                break;
            case 22:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(12).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(11).getKey() > 1){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() - 1);
                        }
                        if (properties.getProperty(12).getKey() > 1){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() - 1);
                        }
                    }
                }
                break;
            case 23:
                if (properties.getProperty(15).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(15).getKey() > 1){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() - 1);
                        }
                        if (properties.getProperty(16).getKey() > 1){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() - 1);
                        }
                    }
                }
                break;
            case 24:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(14).getKey() > 1){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() - 1);
                        }
                        if (properties.getProperty(16).getKey() > 1){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() - 1);
                        }
                    }
                }
                break;
            case 26:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(15).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(14).getKey() > 1){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() - 1);
                        }
                        if (properties.getProperty(15).getKey() > 1){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() - 1);
                        }
                    }
                }
                break;
            case 28:
                if (properties.getProperty(18).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(18).getKey() > 1){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() - 1);
                        }
                        if (properties.getProperty(19).getKey() > 1){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() - 1);
                        }
                    }
                }
                break;
            case 30:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(17).getKey() > 1){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() - 1);
                        }
                        if (properties.getProperty(19).getKey() > 1){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() - 1);
                        }
                    }
                }
                break;
            case 31:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(18).isStatus()){
                        if (property.getKey() > 1){
                            property.setKey(property.getKey() - 1);
                        }
                        if (properties.getProperty(17).getKey() > 1){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() - 1);
                        }
                        if (properties.getProperty(18).getKey() > 1){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() - 1);
                        }
                    }
                }
                break;
            case 33:
                if (properties.getProperty(21).isStatus()){
                    if (property.getKey() > 1){
                        property.setKey(property.getKey() - 1);
                    }
                    if (properties.getProperty(21).getKey() > 1){
                        properties.getProperty(21).setKey(properties.getProperty(21).getKey() - 1);
                    }
                }
                break;
            case 35:
                if (properties.getProperty(20).isStatus()){
                    if (property.getKey() > 1){
                        property.setKey(property.getKey() - 1);
                    }
                    if (properties.getProperty(20).getKey() > 1){
                        properties.getProperty(20).setKey(properties.getProperty(20).getKey() - 1);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void setKeysNearDown(Property property, Properties properties){
        switch (property.getTableNo()){
            case 1:
                if (properties.getProperty(1).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(1).getKey() > 1){
                        properties.getProperty(1).setKey(properties.getProperty(1).getKey() - 1);
                    }
                }
                break;
            case 3:
                if (properties.getProperty(0).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(0).getKey() > 1){
                        properties.getProperty(0).setKey(properties.getProperty(0).getKey() - 1);
                    }
                }
                break;
            case 5:
                if (properties.getProperty(3).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(3).getKey() > 1){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() - 1);
                        }
                        if (properties.getProperty(4).getKey() > 1){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() - 1);
                        }
                    }
                }
                break;
            case 6:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(4).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(2).getKey() > 1){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() - 1);
                        }
                        if (properties.getProperty(4).getKey() > 1){
                            properties.getProperty(4).setKey(properties.getProperty(4).getKey() - 1);
                        }
                    }
                }
                break;
            case 8:
                if (properties.getProperty(2).isStatus()){
                    if (properties.getProperty(3).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(2).getKey() > 1){
                            properties.getProperty(2).setKey(properties.getProperty(2).getKey() - 1);
                        }
                        if (properties.getProperty(3).getKey() > 1){
                            properties.getProperty(3).setKey(properties.getProperty(3).getKey() - 1);
                        }
                    }
                }
                break;
            case 10:
                if (properties.getProperty(6).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(6).getKey() > 1){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() - 1);
                        }
                        if (properties.getProperty(7).getKey() > 1){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() - 1);
                        }
                    }
                }
                break;
            case 12:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(7).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(5).getKey() > 1){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() - 1);
                        }
                        if (properties.getProperty(7).getKey() > 1){
                            properties.getProperty(7).setKey(properties.getProperty(7).getKey() - 1);
                        }
                    }
                }
                break;
            case 13:
                if (properties.getProperty(5).isStatus()){
                    if (properties.getProperty(6).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(5).getKey() > 1){
                            properties.getProperty(5).setKey(properties.getProperty(5).getKey() - 1);
                        }
                        if (properties.getProperty(6).getKey() > 1){
                            properties.getProperty(6).setKey(properties.getProperty(6).getKey() - 1);
                        }
                    }
                }
                break;
            case 14:
                if (properties.getProperty(9).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(9).getKey() > 1){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() - 1);
                        }
                        if (properties.getProperty(10).getKey() > 1){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() - 1);
                        }
                    }
                }
                break;
            case 15:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(10).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(8).getKey() > 1){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() - 1);
                        }
                        if (properties.getProperty(10).getKey() > 1){
                            properties.getProperty(10).setKey(properties.getProperty(10).getKey() - 1);
                        }
                    }
                }
                break;
            case 17:
                if (properties.getProperty(8).isStatus()){
                    if (properties.getProperty(9).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(8).getKey() > 1){
                            properties.getProperty(8).setKey(properties.getProperty(8).getKey() - 1);
                        }
                        if (properties.getProperty(9).getKey() > 1){
                            properties.getProperty(9).setKey(properties.getProperty(9).getKey() - 1);
                        }
                    }
                }
                break;
            case 19:
                if (properties.getProperty(12).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(12).getKey() > 1){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() - 1);
                        }
                        if (properties.getProperty(13).getKey() > 1){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() - 1);
                        }
                    }
                }
                break;
            case 21:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(13).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(11).getKey() > 1){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() - 1);
                        }
                        if (properties.getProperty(13).getKey() > 1){
                            properties.getProperty(13).setKey(properties.getProperty(13).getKey() - 1);
                        }
                    }
                }
                break;
            case 22:
                if (properties.getProperty(11).isStatus()){
                    if (properties.getProperty(12).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(11).getKey() > 1){
                            properties.getProperty(11).setKey(properties.getProperty(11).getKey() - 1);
                        }
                        if (properties.getProperty(12).getKey() > 1){
                            properties.getProperty(12).setKey(properties.getProperty(12).getKey() - 1);
                        }
                    }
                }
                break;
            case 23:
                if (properties.getProperty(15).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(15).getKey() > 1){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() - 1);
                        }
                        if (properties.getProperty(16).getKey() > 1){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() - 1);
                        }
                    }
                }
                break;
            case 24:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(16).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(14).getKey() > 1){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() - 1);
                        }
                        if (properties.getProperty(16).getKey() > 1){
                            properties.getProperty(16).setKey(properties.getProperty(16).getKey() - 1);
                        }
                    }
                }
                break;
            case 26:
                if (properties.getProperty(14).isStatus()){
                    if (properties.getProperty(15).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(14).getKey() > 1){
                            properties.getProperty(14).setKey(properties.getProperty(14).getKey() - 1);
                        }
                        if (properties.getProperty(15).getKey() > 1){
                            properties.getProperty(15).setKey(properties.getProperty(15).getKey() - 1);
                        }
                    }
                }
                break;
            case 28:
                if (properties.getProperty(18).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(18).getKey() > 1){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() - 1);
                        }
                        if (properties.getProperty(19).getKey() > 1){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() - 1);
                        }
                    }
                }
                break;
            case 30:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(19).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(17).getKey() > 1){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() - 1);
                        }
                        if (properties.getProperty(19).getKey() > 1){
                            properties.getProperty(19).setKey(properties.getProperty(19).getKey() - 1);
                        }
                    }
                }
                break;
            case 31:
                if (properties.getProperty(17).isStatus()){
                    if (properties.getProperty(18).isStatus()){
                        if (property.getKey() < 5){
                            property.setKey(property.getKey() + 1);
                        }
                        if (properties.getProperty(17).getKey() > 1){
                            properties.getProperty(17).setKey(properties.getProperty(17).getKey() - 1);
                        }
                        if (properties.getProperty(18).getKey() > 1){
                            properties.getProperty(18).setKey(properties.getProperty(18).getKey() - 1);
                        }
                    }
                }
                break;
            case 33:
                if (properties.getProperty(21).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(21).getKey() > 1){
                        properties.getProperty(21).setKey(properties.getProperty(21).getKey() - 1);
                    }
                }
                break;
            case 35:
                if (properties.getProperty(20).isStatus()){
                    if (property.getKey() < 5){
                        property.setKey(property.getKey() + 1);
                    }
                    if (properties.getProperty(20).getKey() > 1){
                        properties.getProperty(20).setKey(properties.getProperty(20).getKey() - 1);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void playCard(Card card, Players players, Properties allProperties){
        switch (card.getNo()){
            case 0:
                for (int i = 0; i < players.size(); i++) {
                    if (!players.getPlayer(i).isJail()){
                        players.getPlayer(i).setPlace(18);
                    }
                }
                break;
            case 1:
                setMoney(getMoney() - (getProperties().size() * 50));
                break;
            case 2:
                chooseBiggestValue();
                break;
            case 3:
            case 8:
                chooseSmallestValue();
                break;
            case 4:
            case 9:
                Player playerTmp = players.worstTotalWorth(this);
                playerTmp.setMoney(playerTmp.getMoney() + 200);
                setMoney(getMoney() + 200);
                break;
            case 5:
                chooseSmallestValue();
                break;
            case 6:
                players.setMarathon();
                break;
            case 7:
                Player playerT = players.bestTotalWorth(this);
                playerT.setJail(true);
                playerT.setPlace(9);
                break;
            case 16:
            case 11:
            case 10:
                goOrIncreaseProperty(allProperties);
                break;
            case 15:
            case 14:
            case 13:
            case 12:
                Property property1 = canIncrease();
                if (property1 != null){
                    setKeysAllUp(property1, allProperties);
                }
                break;
            case 18:
            case 17:
                changeProperty(players, allProperties);
                break;
            case 20:
            case 19:
                Property property2 = canIncrease();
                if (property2 != null){
                    setKeysNearDown(property2, allProperties);
                }
                break;
            case 21:
            case 22:
                Property property3 = getSmallestValue();
                if (properties.size() > 0){
                    setKeysAllDown(property3, allProperties);
                }
                break;
            default:
                break;
        }
    }

    public Property getSmallestValue(){
        Property property = null;
        int smallest = 100000;
        for (int i = 0; i < properties.size(); i++) {
            int key = properties.getProperty(i).getKey();
            if (properties.getProperty(i).getPropertyValue().get(key) < smallest){
                property = properties.getProperty(i);
                smallest = properties.getProperty(i).getPropertyValue().get(key);
            }
        }
        return property;
    }

    public int findIndex(Property property){
        int index = -1;
        for (int i = 0; i < getProperties().size(); i++) {
            if (getProperties().getProperty(i).equals(property)){
                index = i;
                break;
            }
        }
        return index;
    }

    public Property canIncrease(){
        Property property = null;
        for (int i = 0; i < getProperties().size(); i++) {
            if (getProperties().getProperty(getProperties().size() - i - 1).getKey() < 5){
                property = getProperties().getProperty(getProperties().size() - i - 1);
                break;
            }
        }
        return property;
    }

    protected void addOnlyProperty(Property property){
        properties.addProperty(property);
    }

    public void addProperty(Property property){
        if (!property.isStatus()){
            properties.addProperty(property);
            properties.getProperty(properties.size() - 1).setKey(properties.getProperty(properties.size() - 1).getKey() + 1);
            properties.getProperty(properties.size() - 1).setStatus(true);
            setMoney(getMoney() - properties.getProperty(properties.size() - 1).getPrice());
        }
    }

    public void jailType(){
        if (isJail()) {
            if (toursInJail == 3) {
                setJail(false);
                toursInJail = 0;
            }
        }
    }

    public boolean containsKeyValueOne(){
        boolean bool = false;
        for (int i = 0; i < getProperties().size(); i++) {
            if (getProperties().getProperty(i).getKey() == 1){
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void setMarathon(){
        marathonMode = true;
    }

    public Property containsFalseStatus(Properties properties){
        Property property = null;
        for (int i = 0; i < properties.size(); i++) {
            if (!properties.getProperty(i).isStatus()){
                property = properties.getProperty(i);
                break;
            }
        }
        return property;
    }

    public boolean isJail() {
        return jail;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Properties getProperties() {
        return properties;
    }

    public int sizeProperties() {
        return properties.size();
    }
}
