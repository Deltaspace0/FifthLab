package ConsoleAPP.exceptions;

/**
 * Исключение, которое бросается, когда пытаются прочитать
 * файл, а это оказывается директория.
 */

public class NotFileButDirectoryException extends InvalidFieldException {
    public NotFileButDirectoryException(String filePath) {
        super("файл, это вообще-то директория (" + filePath + ")");
    }
}
