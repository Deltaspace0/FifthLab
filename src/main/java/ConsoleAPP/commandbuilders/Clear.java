package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Clear implements CommandBuilder {
    private final CollectionManager manager;

    public Clear(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
