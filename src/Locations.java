import java.util.HashSet;

public class Locations {

    private final HashSet<Location> locations;

    public Locations(){
        this.locations = new HashSet<>();
    }

    public void addLocation(Location location){
        locations.add(location);
    }

    public boolean containsTableNo(int place){
        boolean bool = false;
        for (Location location : locations) {
            if (location.getTableNo() == place) {
                bool = true;
                break;
            }
        }
        return bool;
    }
}
