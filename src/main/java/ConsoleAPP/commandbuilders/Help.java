package ConsoleAPP.commandbuilders;

import java.util.HashMap;

/**
 * Этот строитель команд отвечает за справку ко всем командам.
 * У каждого строителя команд есть метод getDescription(), который
 * возвращает строку, поясняющую, что делает команда.
 * <p>
 * А у Help есть метод addDescription, который вызывается каждый раз,
 * когда в Core добавляется новая команда (на данный момент в Core
 * команды добавляются только внутри конструктора).
 * <p>
 * Метод у новой команды
 * вызывает getDescription() и получает описание. Затем описание
 * к команде отправляется в HashMap, а также прикрепляется к большой
 * справке по всем командам, которая выводится, когда пользователь
 * вводит help без аргументов.
 * <p>
 * Если ввести help "название команды",
 * то выведется справка только об этой команде.
 */

public class Help implements CommandBuilder {
    private StringBuilder defaultHelpString = new StringBuilder();
    private final HashMap<String, String> descriptions = new HashMap<>();

    public void addDescription(String commandName, String description) {
        descriptions.put(commandName, description);
        defaultHelpString.append(description).append("\n");
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            if (tokens.length > 1) {
                if (descriptions.containsKey(tokens[1])) {
                    System.out.println(descriptions.get(tokens[1]));
                } else {
                    System.out.println("По команде " + tokens[1] + " справки нет.");
                }
            } else {
                System.out.println(defaultHelpString);
            }
        };
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
