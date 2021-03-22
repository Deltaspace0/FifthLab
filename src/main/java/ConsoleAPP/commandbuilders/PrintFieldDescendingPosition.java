package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;

public class PrintFieldDescendingPosition implements CommandBuilder {
    private final CollectionManager manager;

    public PrintFieldDescendingPosition(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return null;
    }

    @Override
    public String getDescription() {
        return "print_field_descending_position : вывести значения поля position всех элементов в порядке убывания";
    }
}
