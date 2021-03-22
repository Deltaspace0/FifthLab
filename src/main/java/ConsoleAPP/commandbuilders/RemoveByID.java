package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class RemoveByID implements CommandBuilder {
    private final CollectionManager manager;

    public RemoveByID(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
