package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

import java.util.Scanner;

public class InputProvider<T> {
    private final String invitation;
    private final InputGetter<T> inputGetter;
    private Scanner scanner = new Scanner(System.in);

    public interface InputGetter<T> {
        T get(String input) throws InputException;
    }

    public InputProvider(String invitation, InputGetter<T> inputGetter) {
        this.invitation = invitation;
        this.inputGetter = inputGetter;
    }

    public T provide() {
        while (true) {
            System.out.print(invitation);
            if (!scanner.hasNextLine()) {
                System.out.println("А зачем нажимать Ctrl+D?");
                scanner = new Scanner(System.in);
                continue;
            }
            String input = scanner.nextLine().trim().replaceAll("( )+", " ");
            T result;
            try {
                result = inputGetter.get(input);
            } catch (InputException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
            return result;
        }
    }
}
