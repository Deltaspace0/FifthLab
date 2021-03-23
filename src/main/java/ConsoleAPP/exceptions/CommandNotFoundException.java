package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда пользователь
 * либо скрипт вводит несуществующую команду.
 */

public class CommandNotFoundException extends InputException {
    public CommandNotFoundException(String commandName) {
        super("К сожалению, нет такой команды: " + commandName);
    }
}
