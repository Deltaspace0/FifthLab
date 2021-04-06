package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда к файлу
 * запрещён доступ, да так, что его не только читать,
 * но и писать туда даже нельзя.
 */

public class FileCannotBeReadAndWrittenException extends InvalidFieldException {
    public FileCannotBeReadAndWrittenException(String filePath) {
        super("то, чтобы можно было этот файл (" + filePath + ") читать и писать туда");
    }
}
