package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда к файлу
 * запрещён доступ, а прочитать его надо.
 */

public class FileCannotBeReadException extends InvalidFieldException {
    public FileCannotBeReadException(String filePath) {
        super("то, чтобы этот файл (" + filePath + ") можно было читать");
    }
}
