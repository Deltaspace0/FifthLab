package ConsoleAPP.commands;

public class Add implements Command {
    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
