package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.exceptions.InputException;

public class Save implements CommandBuilder {
    private final CollectionManager manager;

    public Save(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            try {
                manager.saveToFile();
                System.out.println("Коллекция сохранена в файл.");
            } catch (InputException exception) {
                System.out.println(exception.getMessage());
            }
        };
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
