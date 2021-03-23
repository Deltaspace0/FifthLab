package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.ExitException;

/**
 * Это функциональный интерфейс для лямбд,
 * возвращаемых строителями команд.
 * Здесь написано, что метод бросает ExitException.
 * Это для специальной команды Exit.
 *
 * @see Exit
 */

public interface Command {
    void execute() throws ExitException;
}
