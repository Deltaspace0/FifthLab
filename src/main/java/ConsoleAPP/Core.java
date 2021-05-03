package ConsoleAPP;

import ConsoleAPP.commandbuilders.*;
import ConsoleAPP.exceptions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 * Ядро программы, которое управляет командами.
 */

public class Core {
    private final HashMap<String, CommandBuilder> availableCommandBuilders = new HashMap<>();
    private final BiConsumer<String, String> addDescription;
    private final Consumer<String[]> addHistoryLine;

    public Core(String filePath) {
        this(new HashSet<>(), new CollectionManager(filePath));
    }

    /**
     * Перегрузка конструктора на случай, если идёт запуск скрипта
     * и это теперь подъядро, исполняющее команды не пользователя, а скрипта.
     * @param superCoreScripts
     * @param manager
     */

    public Core(HashSet<String> superCoreScripts, CollectionManager manager) {
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
        addCommandBuilder(new ExecuteScript(superCoreScripts, manager));
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

    /**
     * Этот метод берёт на себя ввод пользователя и ищет команду.
     * Если не находит - во всём винит пользователя и говорит, что такой
     * команды нет, бросая соответствующее исключение (CommandNotFoundException).
     * Если находит, то строит лямбду, которую затем упаковывает в Request
     * вместе с addHistoryLine.
     *
     *
     * @param input
     * @return
     * @throws InputException
     */

    public Request buildRequest(String input) throws InputException {
        if (input.equals(""))
            return () -> {};
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
