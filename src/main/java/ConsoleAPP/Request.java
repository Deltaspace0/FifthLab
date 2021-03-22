package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

public interface Request {
    void execute() throws ExitException;
}
