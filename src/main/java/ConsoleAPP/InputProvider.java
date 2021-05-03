package ConsoleAPP;

import ConsoleAPP.exceptions.InputException;

import java.util.function.BooleanSupplier;

/**
 * Этот класс позволяет упростить взаимодействие с пользователем.
 * Ему передаётся строка-приглашение к вводу и валидатор,
 * который бросает исключения, если ввод не соответствует каким-либо
 * критериям, или возвращает готовый объект.
 * <p>
 * Ввод происходит в бесконечном цикле, поэтому если пользователь
 * ввёл что-то неправильное, ему автоматически предложат ввести
 * снова.
 * <p>
 * В конструктор также может передаваться стоп-лямбда, по которой
 * определяется, в какой момент прервать цикл.
 * @param <T>
 */

public class InputProvider<T> {
    private final String invitation;
    private final Validator<T> validator;
    private final BooleanSupplier needToStop;

    public InputProvider(String invitation, Validator<T> validator) {
        this(invitation, validator, () -> false);
    }

    public InputProvider(String invitation, Validator<T> validator, BooleanSupplier needToStop) {
        this.invitation = invitation;
        this.validator = validator;
        this.needToStop = needToStop;
    }

    public T provide() {
        while (!needToStop.getAsBoolean()) {
            System.out.print(invitation);
            String input = InterScanner.nextLine();
            if (input == null)
                continue;
            input = input.trim().replaceAll("( )+", " ");
            T result;
            try {
                result = validator.get(input);
            } catch (InputException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
            return result;
        }
        return null;
    }
}
