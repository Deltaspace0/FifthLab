package ConsoleAPP;

import ConsoleAPP.commandbuilders.*;
import ConsoleAPP.exceptions.*;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class Core {
    private final HashMap<String, CommandBuilder> availableCommandBuilders = new HashMap<>();
    private final BiConsumer<String, String> addDescription;
    private final Consumer<String[]> addHistoryLine;

    public Core() {
        CollectionManager manager = new CollectionManager();
        Help help = new Help();
        History history = new History();
        addDescription = help::addDescription;
        addHistoryLine = history::addHistoryLine;
        addCommandBuilder(help);
        addCommandBuilder(new Info(manager));
        addCommandBuilder(new Show(manager));
        addCommandBuilder(new Add(manager));
        addCommandBuilder(new Update(manager));
        addCommandBuilder(new RemoveByID(manager));
        addCommandBuilder(new Clear(manager));
        addCommandBuilder(new Save(manager));
        addCommandBuilder(new ExecuteScript());
        addCommandBuilder(new Exit());
        addCommandBuilder(new AddIfMax(manager));
        addCommandBuilder(new AddIfMin(manager));
        addCommandBuilder(history);
        addCommandBuilder(new MaxByStatus(manager));
        addCommandBuilder(new PrintAscending(manager));
        addCommandBuilder(new PrintFieldDescendingPosition(manager));
    }

    public void addCommandBuilder(CommandBuilder builder) {
        String description = builder.getDescription();
        String commandName = description.split(" ")[0];
        availableCommandBuilders.put(commandName, builder);
        addDescription.accept(commandName, description);
    }

    public Request buildRequest(String input) throws CommandNotFoundException {
        String[] tokens = input.split(" ");
        for (String commandName : availableCommandBuilders.keySet()) {
            if (commandName.equals(tokens[0])) {
                CommandBuilder builder = availableCommandBuilders.get(tokens[0]);
                Command command = builder.build(tokens);
                return () -> {
                    command.execute();
                    addHistoryLine.accept(tokens);
                };
            }
        }
        throw new CommandNotFoundException(tokens[0]);
    }
}
