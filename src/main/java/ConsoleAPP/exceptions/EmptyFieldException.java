package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда
 * пользователь пытается ввести пустое значение поля,
 * а оно не может быть null.
 */

public class EmptyFieldException extends InputException {
    public EmptyFieldException(String fieldName) {
        super("А где " + fieldName + "?");
    }
}
