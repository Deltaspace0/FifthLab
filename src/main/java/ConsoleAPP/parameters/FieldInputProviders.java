package ConsoleAPP.parameters;

import ConsoleAPP.InputProvider;
import ConsoleAPP.exceptions.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Это класс с кучей статических полей - это набор InputProvider'ов,
 * которые отвечают за ввод полей при создании новых элементов.
 * Некоторые инициализируются напрямую с помощью конструктора, в который
 * посылается лямбда. Другие - с помощью специальных строителей,
 * например, провайдеры подтверждения или провайдеры Enum'ов. Такие
 * строители созданы, чтобы уменьшить количество кода, ведь
 * по структуре такие провайдеры очень похожи, и без строителей
 * возникает кошмарная дупликация кода.
 */

public class FieldInputProviders {
    private static final Pattern birthdayPattern = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");

    private static InputProvider<Boolean> confirmationInputProviderBuilder(String fieldName) {
        return new InputProvider<>("Надо вводить " + fieldName + "? (Надо написать yes или вообще ничего): ", input -> {
            if (input.equals(""))
                return false;
            if (input.equalsIgnoreCase("yes"))
                return true;
            throw new InvalidFieldException("yes, так надо вводить " + fieldName + " или всё-таки нет?");
        });
    }

    public static InputProvider<Position> positionInputProvider = new EnumFieldInputProviderBuilder<>("должность", Position.class).inputProvider;
    public static InputProvider<Status>   statusInputProvider   = new EnumFieldInputProviderBuilder<>("статус",      Status.class).inputProvider;
    public static InputProvider<Color>    colorInputProvider    = new EnumFieldInputProviderBuilder<>("цвет волос",   Color.class).inputProvider;

    public static InputProvider<Boolean> statusConfirmationInputProvider   = confirmationInputProviderBuilder("статус");
    public static InputProvider<Boolean> personConfirmationInputProvider   = confirmationInputProviderBuilder("персональные данные");
    public static InputProvider<Boolean> locationConfirmationInputProvider = confirmationInputProviderBuilder("место работы");

    public static InputProvider<String> workerNameInputProvider = new InputProvider<>("Введите имя сотрудника: ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("имя");
        return input;
    });

    public static InputProvider<String> locationNameInputProvider = new InputProvider<>("Введите название места работы: ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("название");
        return input;
    });

    public static InputProvider<Integer> coordinatesXInputProvider = new InputProvider<>("Введите координату X: ", FieldInputProviders::parseIntegerCoordinate);

    public static InputProvider<Double> coordinatesYInputProvider = new InputProvider<>("Введите координату Y (максимально 60): ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("координата");
        double y;
        try {
            y = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFieldException("координату");
        }
        if (y > 60)
            throw new FieldValueTooBigException("60");
        return y;
    });

    public static InputProvider<Integer> salaryInputProvider = new InputProvider<>("Введите зарплату (> 0): ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("зарплата");
        int salary;
        try {
            salary = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFieldException("зарплату");
        }
        if (salary <= 0)
            throw new FieldValueTooSmallException("0");
        return salary;
    });

    public static InputProvider<LocalDateTime> birthdayInputProvider = new InputProvider<>("Введите дату рождения (например, 27.02.2002): ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("дата рождения");
        Matcher matcher = birthdayPattern.matcher(input);
        if (!matcher.matches())
            throw new InvalidFieldException("дату рождения");
        String[] numbers = input.split("\\.");
        int day   = Integer.parseInt(numbers[0]);
        int month = Integer.parseInt(numbers[1]);
        int year  = Integer.parseInt(numbers[2]);
        try {
            return LocalDateTime.of(year, month, day, 0, 0);
        } catch (DateTimeException exception) {
            throw new InvalidFieldException("дату рождения");
        }
    });

    public static InputProvider<Long> locationXInputProvider = new InputProvider<>("Введите координату X у места работы: ", input -> {
        if (input.equals(""))
            throw new EmptyFieldException("координата");
        try {
            return Long.valueOf(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFieldException("координату");
        }
    });

    public static InputProvider<Integer> locationYInputProvider = new InputProvider<>("Введите координату Y у места работы: ", FieldInputProviders::parseIntegerCoordinate);
    public static InputProvider<Integer> locationZInputProvider = new InputProvider<>("Введите координату Z у места работы: ", FieldInputProviders::parseIntegerCoordinate);

    private static Integer parseIntegerCoordinate(String input) throws InputException {
        if (input.equals(""))
            throw new EmptyFieldException("координата");
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFieldException("координату");
        }
    }
}
