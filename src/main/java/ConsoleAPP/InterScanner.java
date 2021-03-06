package ConsoleAPP;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Это обёртка для сканнера, которая позволяет в обход
 * пользовательского ввода поставить в очередь на обработку
 * другие какие-нибудь строки, чем пользуется, например, ExecuteScript.
 */

public class InterScanner {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayDeque<String> interlines = new ArrayDeque<>();

    public static int countInterLines() {
        return interlines.size();
    }

    public static void interfere(String interline) {
        interlines.addFirst(interline);
    }

    public static String nextLine() {
        return nextLine(true);
    }

    public static String nextLine(boolean needToPrint) {
        if (interlines.isEmpty()) {
            if (!scanner.hasNextLine()) {
                System.out.println("А зачем нажимать Ctrl+D?");
                scanner = new Scanner(System.in);
                return null;
            }
            return scanner.nextLine();
        }
        String interline = interlines.removeFirst();
        if (needToPrint)
            System.out.println(interline);
        return interline;
    }
}
