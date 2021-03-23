package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Add implements CommandBuilder {
    private final CollectionManager manager;

    public Add(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            long ID = manager.initiateElementAddingProcedure();
            System.out.printf("Элемент успешно добавлен в коллекцию (ID: %d).%n", ID);
        };
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
