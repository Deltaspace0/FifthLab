package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

/**
 * Это строитель команды Add.
 * Его метод build возвращает лямбду, которая использует
 * CollectionManager'овский метод initiateElementAddingProcedure() -
 * "инициировать процедуру добавления элемента". ID только что
 * созданного элемента затем высвечивается вместе с сообщением
 * на экране, а ID следующего нового элемента будет на 1 больше.
 *
 * @see CollectionManager
 */

public class Add implements CommandBuilder {
    private final CollectionManager manager;

    public Add(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            long ID = manager.initiateElementAddingProcedure();
            System.out.printf("Элемент успешно добавлен в коллекцию (ID: %d).%n", ID);
        };
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
