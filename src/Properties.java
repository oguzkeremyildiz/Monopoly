import java.util.ArrayList;

public class Properties {

    private ArrayList<Property> properties = new ArrayList<>();

    public Properties(){
    }

    public void addProperty(Property property){
        properties.add(property);
    }

    public Property getProperty(int index){
        return properties.get(index);
    }

    public void removeProperty(int index){
        properties.remove(index);
    }

    public boolean containsProperty(Property property){
        boolean bool = false;
        for (int i = 0; i < size(); i++) {
            if (getProperty(i).equals(property)){
                bool = true;
                break;
            }
        }
        return bool;
    }

    public boolean containsTableNo(int place){
        boolean bool = false;
        for (Property property : properties) {
            if (property.getTableNo() == place) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public int size(){
        return properties.size();
    }
}
