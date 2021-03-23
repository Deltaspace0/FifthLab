package ConsoleAPP.parameters;

public class EnumFieldParserBuilder<T extends Enum<T>> {
    public T parse(String line, Class<T> clazz) {
        for (T enumValue : clazz.getEnumConstants()) {
            if (line.equals(enumValue.name()))
                return enumValue;
        }
        return null;
    }
}
