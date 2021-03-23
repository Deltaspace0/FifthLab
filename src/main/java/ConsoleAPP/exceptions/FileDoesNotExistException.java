package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда происходит
 * попытка прочитать из несуществующего файла.
 */

public class FileDoesNotExistException extends InvalidFieldException {
    public FileDoesNotExistException(String filePath) {
        super("то, чтобы этот файл (" + filePath + ") вообще существовал");
    }
}
