package ConsoleAPP;

import ConsoleAPP.parameters.*;

import java.util.LinkedHashSet;

public class CollectionManager {
    private final LinkedHashSet<Worker> elements = new LinkedHashSet<>();

    public String getInfo() {
        return "себя";
    }

    public String getElements() {
        return "их";
    }

    public void removeByID(String ID) {

    }

    public void clear() {

    }

    public String getCSV() {
        return "csv";
    }

    public String getMaxByStatus() {
        return "max";
    }

    public String getElementsAscending() {
        return "asc";
    }

    public String getPositionsDescending() {
        return "des";
    }
}
