package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

import java.util.Scanner;

/**
 * Это провайдер ввода, который значительно
 * облегчает жизнь. Он берёт на себя всю возню с пользователем,
 * взамен требуя лишь метод проверки, правильно ли что-то
 * пользователь ввёл, или его нужно ещё раз заставить
 * ввести поле. Используется дженерик, чтобы иметь возможность
 * работать со всеми возможными типами, будь то String, Integer или
 * даже Request с Enum'ом.
 *
 *
 * @param <T>
 */

public class InputProvider<T> {
    private final String invitation;
    private final InputGetter<T> inputGetter;
    private Scanner scanner = new Scanner(System.in);

    public interface InputGetter<T> {
        T get(String input) throws InputException;
    }

    /**
     * Используется так: сначала задаётся приглашение к вводу поля.
     * Например: "Введите это и то, пожалуйста: ". Вторым параметром идёт
     * лямбда, которая позволяет проверить, правильно ли пользователь всё
     * ввёл, или он какую-то ерунду написал. Заодно эта лямбда парсит
     * String во что-нибудь более полезное, например, в Double.
     *
     * Проверка идёт таким образом: если бросается InputException - значит
     * неправильно, надо сначала. Если возвращается T, то всё замечательно.
     *
     * @param invitation
     * @param inputGetter
     */

    public InputProvider(String invitation, InputGetter<T> inputGetter) {
        this.invitation = invitation;
        this.inputGetter = inputGetter;
    }

    /**
     * Этот метод запускает бесконечный цикл, вырваться из которого можно
     * только двумя способоами: как-то по-варварски вырубить программу
     * или ввести такой String, который позволит его преобразовать к T.
     *
     * @return
     */

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
