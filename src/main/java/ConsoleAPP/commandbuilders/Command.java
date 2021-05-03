package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.ExitException;

/**
 * Это функциональный интерфейс для лямбд,
 * возвращаемых строителями команд.
 * Метод бросает ExitException, потому что это
 * необходимо для работы команды Exit.
 *
 * @see Exit
 */

public interface Command {
    void execute() throws ExitException;
}
