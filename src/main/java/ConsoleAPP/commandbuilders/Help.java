package ConsoleAPP.commandbuilders;

import java.util.HashMap;

public class Help implements CommandBuilder {
    private StringBuilder defaultHelpString = new StringBuilder();
    private final HashMap<String, String> descriptions = new HashMap<>();

    public void addDescription(String commandName, String description) {
        descriptions.put(commandName, description);
        defaultHelpString.append(description).append("\n");
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            if (tokens.length > 1) {
                if (descriptions.containsKey(tokens[1])) {
                    System.out.println(descriptions.get(tokens[1]));
                } else {
                    System.out.println("По команде " + tokens[1] + " справки нет.");
                }
            } else {
                System.out.println(defaultHelpString);
            }
        };
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
