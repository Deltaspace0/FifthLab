package ConsoleAPP.commandbuilders;

import java.util.LinkedList;

/**
 * History отвечает за историю вызовов команд.
 * <p>
 * Каждый строитель команд возвращает лямбду, а Core потом
 * запаковывает каждую такую лямбду в Request вместе с методом
 * addHistoryLine, так что этот метод вызывается каждый раз, когда
 * вызывается какая-либо команда (лямбда).
 * <p>
 * History сразу же отсекает слишком старые вызовы, чтобы всего
 * вызовов в LinkedList было не больше девяти.
 */

public class History implements CommandBuilder {
    private final LinkedList<String> commandHistory = new LinkedList<>();

    public void addHistoryLine(String[] tokens) {
        commandHistory.add(tokens[0]);
        if (commandHistory.size() > 9)
            commandHistory.remove();
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            for (String commandName : commandHistory) {
                System.out.println(commandName);
            }
            System.out.println();
        };
    }

    @Override
    public String getDescription() {
        return "history : вывести последние 9 команд (без их аргументов)";
    }
}
