package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.exceptions.*;

public class RemoveByID implements CommandBuilder {
    private final CollectionManager manager;

    public RemoveByID(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length > 1) {
            try {
                long ID = Long.parseLong(tokens[1]);
                return () -> {
                    if (manager.removeElement(ID)) {
                        System.out.printf("Элемент (ID: %d) успешно удалён.%n", ID);
                    } else {
                        System.out.println("Нет элемента с таким ID.");
                    }
                };
            } catch (NumberFormatException exception) {
                throw new InvalidFieldException("ID");
            }
        } else {
            throw new EmptyFieldException("ID");
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
