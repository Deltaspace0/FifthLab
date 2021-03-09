package ConsoleAPP.commands;

public class AddIfMax implements Command {
    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
