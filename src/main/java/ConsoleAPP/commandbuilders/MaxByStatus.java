package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.parameters.Worker;

import java.util.Iterator;

public class MaxByStatus implements CommandBuilder {
    private final CollectionManager manager;

    public MaxByStatus(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            Worker maxStatusWorker;
            Iterator<Worker> iterator = manager.elements.iterator();
            if (!iterator.hasNext()) {
                System.out.println("Нет никаких элементов в коллекции.");
                return;
            }
            maxStatusWorker = iterator.next();
            while (iterator.hasNext()) {
                Worker worker = iterator.next();
                if (maxStatusWorker.getStatus() == null || maxStatusWorker.getStatus().compareTo(worker.getStatus()) > 0)
                    maxStatusWorker = worker;
            }
            System.out.println(maxStatusWorker.getInfoString());
        };
    }

    @Override
    public String getDescription() {
        return "max_by_status : вывести любой объект из коллекции, значение поля status которого является максимальным";
    }
}
