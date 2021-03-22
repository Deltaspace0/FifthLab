package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.ExitException;

public interface Command {
    void execute() throws ExitException;
}
