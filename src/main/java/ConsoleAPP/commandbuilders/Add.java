package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Add implements CommandBuilder {
    private final CollectionManager manager;

    public Add(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
