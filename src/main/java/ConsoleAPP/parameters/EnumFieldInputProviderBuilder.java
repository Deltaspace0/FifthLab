package ConsoleAPP.parameters;

import ConsoleAPP.InputProvider;
import ConsoleAPP.exceptions.*;

public class EnumFieldInputProviderBuilder<T extends Enum<T>> {
    public final InputProvider<T> inputProvider;

    public EnumFieldInputProviderBuilder(String fieldName, Class<T> clazz) {
        T[] enumConstants = clazz.getEnumConstants();
        String possibleVariants = getPossibleVariants(enumConstants);
        inputProvider = new InputProvider<>("Введите " + fieldName + " (" + possibleVariants + "): ", input -> {
            if (input.equals(""))
                throw new EmptyFieldException(fieldName);
            for (T enumValue : enumConstants) {
                if (input.equalsIgnoreCase(enumValue.name()))
                    return enumValue;
            }
            throw new InvalidFieldException(fieldName);
        });
    }

    private String getPossibleVariants(T[] enumConstants) {
        int length = enumConstants.length;
        if (length == 0)
            return null;
        if (length == 1)
            return enumConstants[0].name();
        StringBuilder possibleVariants = new StringBuilder();
        for (int i = 0; i < length-2; i++)
            possibleVariants.append(enumConstants[i].name()).append(", ");
        possibleVariants.append(enumConstants[length-2]).append(" или ").append(enumConstants[length-1]);
        return possibleVariants.toString();
    }
}
