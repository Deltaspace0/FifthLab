package ConsoleAPP.parameters;

/**
 * Строитель парсеров енама. Здесь используется такая же схема,
 * как и в EnumFieldInputProviderBuilder. Кода меньше засчёт того,
 * там есть взаимодействие с пользователем, а здесь нет.
 *
 * @see EnumFieldInputProviderBuilder
 * @param <T>
 */

public class EnumFieldParserBuilder<T extends Enum<T>> {
    public T parse(String line, Class<T> clazz) {
        for (T enumValue : clazz.getEnumConstants()) {
            if (line.equals(enumValue.name()))
                return enumValue;
        }
        return null;
    }
}
