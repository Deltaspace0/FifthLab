package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.exceptions.*;

/**
 * Update я сначала сделал как вызов Add и RemoveByID. Но затем пришлось
 * изменить подход, так иначе при обновлении элемента дата его создания
 * сбрасывалась и становилась равной текущей.
 */

public class Update implements CommandBuilder {
    private final CollectionManager manager;

    public Update(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length > 1) {
            try {
                long ID = Long.parseLong(tokens[1]);
                return () -> {
                    if (manager.initiateElementUpdatingProcedure(ID)) {
                        System.out.printf("Элемент (ID: %d) успешно обновлён.%n", ID);
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
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
