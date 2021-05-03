package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

/**
 * Строитель команды Clear. Лямбда, возвращаемая методом build,
 * вызывает CollectionManager'овский метод clear().
 *
 * @see CollectionManager
 */

public class Clear implements CommandBuilder {
    private final CollectionManager manager;

    public Clear(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            manager.clear();
            System.out.println("Очистил коллекцию, теперь там пусто.");
        };
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
