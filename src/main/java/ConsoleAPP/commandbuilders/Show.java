package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.exceptions.*;
import ConsoleAPP.parameters.Worker;

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
