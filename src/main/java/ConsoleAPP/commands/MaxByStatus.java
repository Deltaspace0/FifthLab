package ConsoleAPP.commands;

public class MaxByStatus implements Command {
    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "max_by_status : вывести любой объект из коллекции, значение поля status которого является максимальным";
    }
}
