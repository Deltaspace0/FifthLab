package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.parameters.Worker;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Здесь требуется сортировка уже по определённому полю, в отличие от
 * PrintAscending, поэтому кроме TreeSet используется компаратор.
 * Ему скармливается ссылка на метод getPosition, и теперь с помощью
 * него идёт сравнение элементов.
 *
 * @see PrintAscending
 */

public class PrintFieldDescendingPosition implements CommandBuilder {
    private final CollectionManager manager;

    public PrintFieldDescendingPosition(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            TreeSet<Worker> treeSet = new TreeSet<>(Comparator.comparing(Worker::getPosition).thenComparing(Worker::getSalary));
            treeSet.addAll(manager.elements);
            for (Worker worker : treeSet)
                System.out.println(worker.getPosition());
        };
    }

    @Override
    public String getDescription() {
        return "print_field_descending_position : вывести значения поля position всех элементов в порядке убывания";
    }
}
