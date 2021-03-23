package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.Core;
import ConsoleAPP.FileProcessor;
import ConsoleAPP.Request;
import ConsoleAPP.exceptions.*;

import java.util.HashSet;

public class ExecuteScript implements CommandBuilder {
    private final CollectionManager manager;
    private final HashSet<String> superCoreScripts;

    public ExecuteScript(HashSet<String> superCoreScripts, CollectionManager manager) {
        this.manager = manager;
        this.superCoreScripts = superCoreScripts;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length < 2)
            throw new EmptyFieldException("путь к файлу");
        String script = FileProcessor.readFromFile(tokens[1]);
        if (superCoreScripts.contains(script))
            throw new RecursiveScriptExecutionException(tokens[1]);
        HashSet<String> subCoreScripts = new HashSet<>(superCoreScripts);
        subCoreScripts.add(script);
        Core subCore = new Core(subCoreScripts, manager);
        return () -> {
            System.out.println("Исполняю скрипт " + tokens[1] + "...\n");
            for (String line : script.split("\\r?\\n")) {
                try {
                    Request request = subCore.buildRequest(line);
                    request.execute();
                } catch (InputException exception) {
                    System.out.println(exception.getMessage());
                } catch (ExitException exception) {
                    break;
                }
            }
            System.out.println("\nСкрипт " + tokens[1] + " закончил своё выполнение, выхожу из него...");
        };
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
