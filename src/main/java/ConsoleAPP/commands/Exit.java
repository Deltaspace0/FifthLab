package ConsoleAPP.commands;

import ConsoleAPP.exceptions.ExitException;

public class Exit implements Command {
    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() throws ExitException {
        System.out.println("Закрываюсь...");
        throw new ExitException();
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
