package ConsoleAPP.commandbuilders;

import ConsoleAPP.CollectionManager;
import ConsoleAPP.Core;
import ConsoleAPP.FileProcessor;
import ConsoleAPP.Request;
import ConsoleAPP.exceptions.*;

import java.util.HashSet;

/**
 * Самый продвинутый строитель команд - ExecuteScript. Работает он по такому принципу:
 * он сначала проверяет, не произошёл ли рекурсивный вызов, который неизбежно
 * приведёт к бесконечному циклу. Для этого ему Core даёт список скриптов
 * superCoreScripts, которые запускали другие скрипты, а те ещё и так далее, пока
 * какой-то скрипт не вызвал этот. Если он себя находит в этом списке - значит рекурсия
 * есть и надо прекращать это дело. А если не находит, то создаёт копию этого списка,
 * добавляет в копию себя и после этого создаёт subCore - подъядро, которое будет выполнять
 * команды не пользователя, а этого скрипта. Оно даёт подъядру этот список запущенных
 * скриптов, на случай если подъядро запустит ещё скрипт.
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
        /*
         * Создание копии необходимо, так как подъядро создаётся ещё на стадии
         * строительства лямбды, и её могут ещё не скоро запустить, а в
         * superCoreScripts уже будет лежать этот скрипт и мешаться.
         */
        HashSet<String> subCoreScripts = new HashSet<>(superCoreScripts);
        subCoreScripts.add(script);
        Core subCore = new Core(subCoreScripts, manager);
        return () -> {
            System.out.println("Исполняю скрипт " + tokens[1] + "...\n");
            for (String line : script.split("\\r?\\n")) { // эта регулярка позволяет захватывать как CRLF, так и LF.
                try {
                    Request request = subCore.buildRequest(line);
                    request.execute();
                } catch (InputException exception) {
                    System.out.println(exception.getMessage());
                } catch (ExitException exception) {
                    /*
                     * Если не поймать это исключение, то как только какой-то
                     * скрипт исполнит exit, закроется вообще всё.
                     */
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
