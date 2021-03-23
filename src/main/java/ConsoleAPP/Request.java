package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

/**
 * Это функциональный интерфейс, в который Core упаковывает команду.
 */

public interface Request {
    void execute() throws ExitException;
}
