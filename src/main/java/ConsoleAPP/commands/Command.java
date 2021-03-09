package ConsoleAPP.commands;

import ConsoleAPP.exceptions.ExitException;

public interface Command {
    void prepareForExecution(String[] tokens);
    void execute() throws ExitException;
    String getDescription();
}
