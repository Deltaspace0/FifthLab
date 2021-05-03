package ConsoleAPP.exceptions;

/**
 * Исключение, которое обрабатывается InputProvider'ом и
 * позволяет в бесконечном цикле предлагать ввод определённого поля,
 * пока пользователь наконец не введёт его правильно или не
 * сработает стоп-лямбда.
 *
 * @see ConsoleAPP.InputProvider
 */

public class InputException extends Exception {
    public InputException(String errorMessage) {
        super(errorMessage);
    }
}
