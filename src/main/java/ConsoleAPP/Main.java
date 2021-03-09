package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

public class Main {
    public static void main(String[] args) {
        Core core = new Core();
        InputProvider<RequestExecutor> inputProvider = new InputProvider<>("Введите команду: ", core::getRequestExecutor);
        while (true) {
            RequestExecutor requestExecutor = inputProvider.provide();
            try {
                requestExecutor.execute();
            } catch (ExitException exception) {
                return;
            }
        }
    }
}
