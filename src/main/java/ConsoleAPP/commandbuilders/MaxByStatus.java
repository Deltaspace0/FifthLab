package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class MaxByStatus implements CommandBuilder {
    private final CollectionManager manager;

    public MaxByStatus(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "max_by_status : вывести любой объект из коллекции, значение поля status которого является максимальным";
    }
}
