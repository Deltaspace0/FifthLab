package ConsoleAPP;

import ConsoleAPP.commands.*;
import ConsoleAPP.exceptions.*;

import java.util.HashMap;
import java.util.function.Consumer;

public class Core {
    private final HashMap<String, Command> availableCommands = new HashMap<>();
    private final Consumer<Command> addCommandSideEffects;
    private final Consumer<String[]> executeRequestSideEffects;
    private final CollectionManager manager = new CollectionManager();

    public Core() {
        Help help = new Help();
        History history = new History();
        addCommandSideEffects = help::addDescription;
        executeRequestSideEffects = history::addLine;
        addCommand(help);
        addCommand(new Info());
        addCommand(new Show());
        addCommand(new Add());
        addCommand(new Update());
        addCommand(new RemoveByID());
        addCommand(new Clear());
        addCommand(new Save());
        addCommand(new ExecuteScript());
        addCommand(new Exit());
        addCommand(new AddIfMax());
        addCommand(new AddIfMin());
        addCommand(history);
        addCommand(new MaxByStatus());
        addCommand(new PrintAscending());
        addCommand(new PrintFieldDescendingPosition());
    }

    public void addCommand(Command command) {
        String description = command.getDescription();
        String commandName = description.split(" ")[0];
        availableCommands.put(commandName, command);
        addCommandSideEffects.accept(command);
    }

    public RequestExecutor getRequestExecutor(String request) throws CommandNotFoundException {
        String[] tokens = request.split(" ");
        for (String commandName : availableCommands.keySet()) {
            if (commandName.equals(tokens[0])) {
                Command command = availableCommands.get(tokens[0]);
                command.prepareForExecution(tokens);
                return () -> {
                    command.execute();
                    executeRequestSideEffects.accept(tokens);
                };
            }
        }
        throw new CommandNotFoundException(tokens[0]);
    }
}
