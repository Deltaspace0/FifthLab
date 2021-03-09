package ConsoleAPP.commands;

import java.util.LinkedList;

public class History implements Command {
    private final LinkedList<String> commandHistory = new LinkedList<>();

    public void addLine(String[] tokens) {
        commandHistory.add(tokens[0]);
        if (commandHistory.size() > 9)
            commandHistory.remove();
    }

    @Override
    public void prepareForExecution(String[] tokens) {

    }

    @Override
    public void execute() {
        for (String commandName : commandHistory) {
            System.out.println(commandName);
        }
        System.out.println();
    }

    @Override
    public String getDescription() {
        return "history : вывести последние 9 команд (без их аргументов)";
    }
}
