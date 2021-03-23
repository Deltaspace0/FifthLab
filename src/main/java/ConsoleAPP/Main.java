package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

public class Main {
    public static void main(String[] args) {
        Core core = new Core(args[0]);
        InputProvider<Request> inputProvider = new InputProvider<>("Введите команду: ", core::buildRequest);
        while (true) {
            Request request = inputProvider.provide();
            try {
                request.execute();
            } catch (ExitException exception) {
                return;
            }
        }
    }
}
