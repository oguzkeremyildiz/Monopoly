import java.util.HashMap;

public class Property {

    int price;
    int tableNo;
    HashMap<Integer, Integer> propertyValue;
    int key;
    boolean status;

    public Property(int tableNo, int price, HashMap<Integer, Integer> propertyValue, int key, boolean status){
        this.price = price;
        this.tableNo = tableNo;
        this.propertyValue = propertyValue;
        this.key = key;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public int getTableNo() {
        return tableNo;
    }

    public HashMap<Integer, Integer> getPropertyValue() {
        return propertyValue;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
