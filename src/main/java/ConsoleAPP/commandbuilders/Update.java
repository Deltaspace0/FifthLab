package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Update implements CommandBuilder {
    private final CollectionManager manager;

    public Update(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
