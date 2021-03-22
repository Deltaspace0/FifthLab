package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class PrintAscending implements CommandBuilder {
    private final CollectionManager manager;

    public PrintAscending(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }
}
