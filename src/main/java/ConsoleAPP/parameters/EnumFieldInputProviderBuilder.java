package ConsoleAPP.parameters;

import ConsoleAPP.InputProvider;
import ConsoleAPP.exceptions.*;

/**
 * Это строитель провайдеров Enum'ов. Я сначала делал без него, но
 * заметил, что у меня аж в трёх провайдерах лямбды страшно похожи:
 * в каждой по сути перебирались константы енама, пока не
 * находилась та, что совпадает с вводом пользователя. Я решил
 * выделить всё в отдельный строитель провайдеров, но ему надо было
 * как-то передавать, что за енам надо перебрать.
 *
 * Но в дженерики какой угодно тип здесь не затолкаешь, ведь
 * нужен именно наследующийся от енама. В итоге это выглядит как
 * "<T extends Enum<T>>" - дженерик внутри дженерика.
 * Вскоре я столкнулся с тем, что мне нужно было перебрать константы, а
 * оно мне не давало их получить - T.values() не работало. Связано это
 * с тем, что в Java есть type erasure, который перед запуском программы
 * заменяет T на Object, а Object не имеет метода values().
 *
 * Из-за этого пришлось добавить в конструктор ещё один параметр - Class<T>,
 * который бы позволил вытащить константы с помощью getEnumConstants();
 *
 * Используется всё это так:
 * public static InputProvider<Position> positionInputProvider = new EnumFieldInputProviderBuilder<>("должность", Position.class).inputProvider;
 *
 * @param <T>
 */

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

    /**
     * Пользователю нужно знать, из чего выбирать, и для этого нужно помимо "Введите то-то"
     * написать в скобочках, что вообще можно вводить, вот так:
     * "Введите то-то (ONE, TWO или THREE)".
     *
     * Данный метод getPossibleVariants позволяет сгенерировать содержимое скобок из енама,
     * так что не приходится писать их вручную.
     */

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
