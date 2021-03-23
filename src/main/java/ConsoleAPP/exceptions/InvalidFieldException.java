package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда
 * пользователь вводит какую-то чушь вместо поля.
 * Например буквы вместо числа.
 */

public class InvalidFieldException extends InputException {
    public InvalidFieldException(String fieldName) {
        super("Не, это не похоже на " + fieldName + ".");
    }
}
