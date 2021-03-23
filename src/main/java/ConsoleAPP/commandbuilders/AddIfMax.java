package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.parameters.Worker;

public class AddIfMax implements CommandBuilder {
    private final CollectionManager manager;

    public AddIfMax(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            long ID = manager.initiateElementAddingProcedure();
            int salary = manager.workersByID.get(ID).getSalary();
            for (Worker worker : manager.elements) {
                if (salary <= worker.getSalary() && worker.getID() != ID) {
                    System.out.println("А вот у работника с ID " + worker.getID() + " зарплата не меньше, поэтому я ничего в коллекцию не добавлю.");
                    manager.removeElement(ID);
                    return;
                }
            }
            System.out.printf("Элемент успешно добавлен в коллекцию (ID: %d).%n", ID);
        };
    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
