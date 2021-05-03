package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

/**
 * Это функциональный интерфейс, который используется InputProvider,
 * чтобы проверять на корректность какой-либо ввод.
 * @param <T>
 */

public interface Validator<T> {
    T get(String input) throws InputException;
}
