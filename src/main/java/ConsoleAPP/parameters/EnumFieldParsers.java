package ConsoleAPP.parameters;

public class EnumFieldParsers {
    private static final EnumFieldParserBuilder<Position> positionEnumFieldParserBuilder = new EnumFieldParserBuilder<>();
    private static final EnumFieldParserBuilder<Status>   statusEnumFieldParserBuilder   = new EnumFieldParserBuilder<>();
    private static final EnumFieldParserBuilder<Color>    colorEnumFieldParserBuilder    = new EnumFieldParserBuilder<>();

    public static Position parsePosition(String line) {
        return positionEnumFieldParserBuilder.parse(line, Position.class);
    }

    public static Status parseStatus(String line) {
        return statusEnumFieldParserBuilder.parse(line, Status.class);
    }

    public static Color parseColor(String line) {
        return colorEnumFieldParserBuilder.parse(line, Color.class);
    }
}
