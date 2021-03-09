package ConsoleAPP.commands;

public class Show implements Command {
    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
