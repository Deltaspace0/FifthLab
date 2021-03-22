package ConsoleAPP.commandbuilders;

import java.util.LinkedList;

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
