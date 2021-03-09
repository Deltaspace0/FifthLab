package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

public interface RequestExecutor {
    void execute() throws ExitException;
}
