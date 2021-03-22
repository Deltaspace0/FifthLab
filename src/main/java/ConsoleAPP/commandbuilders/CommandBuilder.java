package ConsoleAPP.commandbuilders;

public interface CommandBuilder {
    Command build(String[] tokens);
    String getDescription();
}
