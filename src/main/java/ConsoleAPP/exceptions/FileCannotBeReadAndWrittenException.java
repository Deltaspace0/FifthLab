package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда к файлу
 * запрещён доступ, причём его не только читать нельзя,
 * но и даже писать туда запрещено.
 */

public class FileCannotBeReadAndWrittenException extends InvalidFieldException {
    public FileCannotBeReadAndWrittenException(String filePath) {
        super("то, чтобы можно было этот файл (" + filePath + ") читать и писать туда");
    }
}
