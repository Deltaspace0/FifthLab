package ConsoleAPP;

import ConsoleAPP.exceptions.*;

import java.io.*;

/**
 * Это обработчик файлов. Он умеет читать и писать в них,
 * иногда не умеет, по разным причинам, например, доступа к файлу нет.
 * В этих случаях он бросает соответствующие исключения.
 */

public class FileProcessor {
    /**
     * Читать файл. Реализовано с помощью класса InputStreamReader.
     *
     * @param filePath
     * @return
     * @throws InputException
     */
    public static String readFromFile(String filePath) throws InputException {
        File file = new File(filePath);
        if (!file.exists())
            throw new FileDoesNotExistException(filePath);
        if (file.isDirectory())
            throw new NotFileButDirectoryException(filePath);
        if (!file.canRead() && !file.canWrite())
            throw new FileCannotBeReadAndWrittenException(filePath);
        if (!file.canRead())
            throw new FileCannotBeReadException(filePath);
        try {
            Reader in = new InputStreamReader(new FileInputStream(filePath));
            char[] fileContent = new char[(int) file.length()];
            in.read(fileContent);
            in.close();
            return String.valueOf(fileContent);
        } catch (FileNotFoundException exception) {
            throw new InputException("Это по идее вообще не должно было произойти, хмм...");
        } catch (IOException exception) {
            throw new InvalidFieldException("то, чтобы файл нормально прочитался, вот что выкидывает:\n" + exception.getMessage());
        }
    }

    /**
     * Писать в файл. Реализовано с помощью класса BufferedOutputStream.
     *
     * @param filePath
     * @param content
     * @throws InputException
     */

    public static void saveToFile(String filePath, String content) throws InputException {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            bufferedOutputStream.write(content.getBytes());
            bufferedOutputStream.close();
        } catch (Exception exception) {
            throw new InputException(exception.getMessage());
        }
    }
}
