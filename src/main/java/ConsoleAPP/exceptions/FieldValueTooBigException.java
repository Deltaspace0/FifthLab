package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда пользователь
 * вводит слишком большое значение поля. Например, координату Y у элемента.
 */

public class FieldValueTooBigException extends InputException {
    public FieldValueTooBigException(String maxValue) {
        super("Не, слишком много! Надо, чтобы было меньше " + maxValue + ".");
    }
}
