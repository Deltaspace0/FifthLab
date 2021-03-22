package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class Save implements CommandBuilder {
    private final CollectionManager manager;

    public Save(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
