package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Show implements CommandBuilder {
    private final CollectionManager manager;

    public Show(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
