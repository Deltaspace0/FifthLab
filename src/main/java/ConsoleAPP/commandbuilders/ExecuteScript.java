package ConsoleAPP.commandbuilders;

import ConsoleAPP.*;
import ConsoleAPP.exceptions.*;

import java.util.HashSet;

/**
 * Это строитель команды ExecuteScript. Сначала загружает
 * содержимое скрипта до исполнения команды, а затем при
 * исполнении команды исполняет, всё что записано внутри скрипта.
 * <p>
 * При этом учитывается ограничение на циклический вызов скриптов
 * (то есть оно не позволяет какому-либо из скриптов сделать
 * рекурсивный вызов напрямую или через другие скрипты).
 */

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
            String[] lines = script.split("\\r?\\n");
            int previousSize = InterScanner.countInterLines();
            for (int i = lines.length-1; i >= 0; i--)
                InterScanner.interfere(lines[i]);
            while (true) {
                InputProvider<Request> inputProvider = new InputProvider<>("Введите команду: ",
                        subCore::buildRequest, () -> InterScanner.countInterLines() == previousSize);
                Request request = inputProvider.provide();
                if (request == null)
                    break;
                try {
                    request.execute();
                } catch (ExitException exception) {
                    while (InterScanner.countInterLines() != previousSize)
                        InterScanner.nextLine(false);
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
