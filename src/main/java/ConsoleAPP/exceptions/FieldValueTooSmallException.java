package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда пользователь
 * вводит слишком маленькое или даже отрицательное значение поля.
 * Например, делает зарплату работнику минусовую.
 */

public class FieldValueTooSmallException extends InputException {
    public FieldValueTooSmallException(String minValue) {
        super("Не, слишком мало! Надо, чтобы было больше " + minValue + ".");
    }
}
