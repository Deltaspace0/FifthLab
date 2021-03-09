package ConsoleAPP.commands;

import java.util.HashMap;

public class Help implements Command {
    String defaultHelpString = "";
    HashMap<String, String> descriptions = new HashMap<>();
    String commandName = null;

    public void addDescription(Command command) {
        String description = command.getDescription();
        String commandName = description.split(" ")[0];
        descriptions.put(commandName, description);
        defaultHelpString += description + "\n";
    }

    @Override
    public void prepareForExecution(String[] tokens) {
        if (tokens.length > 1) {
            commandName = tokens[1];
        }
    }

    @Override
    public void execute() {
        if (commandName == null || !descriptions.containsKey(commandName)) {
            System.out.println(defaultHelpString + "\n");
        } else {
            System.out.println(descriptions.get(commandName));
            commandName = null;
        }
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
