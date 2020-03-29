public class Jail {

    int tableNo;

    public Jail(int tableNo){
        this.tableNo = tableNo;
    }

    public int getTableNo() {
        return tableNo;
    }

    public boolean containsTableNo(int place){
        boolean bool = false;
        if (getTableNo() == place){
            bool = true;
        }
        return bool;
    }
}
