package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.exceptions.*;
import ConsoleAPP.parameters.Worker;

/**
 * Show если вызывается без аргументов, выводит все элементы подряд,
 * а если дать ему ID, то выведет только элемент с таким ID. (как help)
 * Чтобы вывести информацию об элементе, он сначала берёт у элемента
 * подготовленную отформатированную строку, а потом выводит её на экран.
 *
 * @see Worker
 */

public class Show implements CommandBuilder {
    private final CollectionManager manager;

    public Show(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length < 2) {
            return () -> {
                for (Worker worker : manager.elements)
                    System.out.println(worker.getInfoString());
            };
        }
        try {
            long ID = Long.parseLong(tokens[1]);
            return () -> {
                Worker worker = manager.workersByID.get(ID);
                if (worker == null) {
                    System.out.println("Нет элемента с таким ID.");
                } else {
                    System.out.println(worker.getInfoString());
                }
            };
        } catch (NumberFormatException exception) {
            throw new InvalidFieldException("ID");
        }
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
