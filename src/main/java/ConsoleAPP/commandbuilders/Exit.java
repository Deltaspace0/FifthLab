package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.ExitException;

/**
 * Это Exit, который просто бросает специальное исключение.
 * Это исключение затем обрабатывается Main'ом и программа закрывается.
 */

public class Exit implements CommandBuilder {
    @Override
    public Command build(String[] tokens) {
        return () -> {
            System.out.println("Закрываюсь...");
            throw new ExitException();
        };
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
