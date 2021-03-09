package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

import java.util.Scanner;

public class InputProvider<T> {
    private final String invitation;
    private final Getter<T> getter;
    private final Scanner scanner = new Scanner(System.in);

    interface Getter<T> {
        T get(String input) throws InputException;
    }

    public InputProvider(String invitation, Getter<T> getter) {
        this.invitation = invitation;
        this.getter = getter;
    }

    public T provide() {
        while (true) {
            System.out.print(invitation);
            String input = scanner.nextLine().trim();
            T result;
            try {
                result = getter.get(input);
            } catch (InputException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
            return result;
        }
    }
}
