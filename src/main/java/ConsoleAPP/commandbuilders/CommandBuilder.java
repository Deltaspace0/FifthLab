package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.InputException;

/**
 * Интерфейс для строителей команд, чтобы можно было
 * их объединить и работать с ними, как с единым типом
 * (и содержать их в HashMap в Core).
 *
 * @see ConsoleAPP.Core
 */

public interface CommandBuilder {
    Command build(String[] tokens) throws InputException;
    String getDescription();
}
