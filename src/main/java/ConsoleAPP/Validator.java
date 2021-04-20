package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

public interface Validator<T> {
    T get(String input) throws InputException;
}
