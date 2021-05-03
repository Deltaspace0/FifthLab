package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросает ExecuteScript при попытке
 * исполнить скрипт, делающий рекурсивный вызов.
 */

public class RecursiveScriptExecutionException extends InputException {
    public RecursiveScriptExecutionException(String fileName) {
        super("При попытке исполнить скрипт в файле " + fileName + " был зафиксирован рекурсивный вызов, который привёл бы к бесконечному циклу. Запуск скрипта остановлен.");
    }
}
