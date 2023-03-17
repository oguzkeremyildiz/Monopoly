public class Jail {

    private final int tableNo;

    public Jail(int tableNo){
        this.tableNo = tableNo;
    }

    public int getTableNo() {
        return tableNo;
    }

    public boolean containsTableNo(int place){
        return getTableNo() == place;
    }
}
