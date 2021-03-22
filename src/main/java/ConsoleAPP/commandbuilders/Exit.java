package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.ExitException;

public class Exit implements CommandBuilder {
    @Override
    public Command build(String[] tokens) {
        return () -> {
            System.out.println("Закрываюсь...");
            throw new ExitException();
        };
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
