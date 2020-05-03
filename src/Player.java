import java.util.Random;

public abstract class Player<Type> {

    int place;
    int money;
    Properties properties;
    Type name;
    boolean jail;
    int toursInJail = 0;
    boolean marathonMode = false;
    int paymentsInMarathon = 0;
    Random dice;

    protected abstract void sellProperty();
    protected abstract void giveProperty(Player player2);
    protected abstract void increaseProperty(Players players, Property property);
    protected abstract void chooseBiggestValue();
    protected abstract void chooseSmallestValue();
    public abstract void play(Properties properties, Players players, Jail jail, Locations locations, CardLocations cardLocations, Cards cards);
    protected abstract void goOrIncreaseProperty(Properties properties);
    protected abstract void changeProperty(Players players, Properties properties);

    public Player(Type name, int place, int money, Properties properties, boolean jail, Random dice){
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

    public Type getName() {
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
