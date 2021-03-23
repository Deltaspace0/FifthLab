package ConsoleAPP.commandbuilders;

import ConsoleAPP.exceptions.InputException;

public interface CommandBuilder {
    Command build(String[] tokens) throws InputException;
    String getDescription();
}
