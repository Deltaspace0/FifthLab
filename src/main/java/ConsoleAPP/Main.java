package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

/**
 * Main
 */

public class Main {
    public static void main(String[] args) {
        Core core = new Core(System.getenv("THETABLE"));
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
