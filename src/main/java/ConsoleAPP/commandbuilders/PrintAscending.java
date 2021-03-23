package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.parameters.Worker;

import java.util.TreeSet;

public class PrintAscending implements CommandBuilder {
    private final CollectionManager manager;

    public PrintAscending(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            TreeSet<Worker> treeSet = new TreeSet<>(manager.elements);
            for (Worker worker : treeSet)
                System.out.println(worker.getInfoString());
        };
    }

    @Override
    public String getDescription() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }
}
