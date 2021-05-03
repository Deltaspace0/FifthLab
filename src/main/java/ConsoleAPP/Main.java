package ConsoleAPP;

import ConsoleAPP.exceptions.ExitException;

/**
 * Здесь инициализируется ядро, которому передаётся путь к файлу,
 * где сохранена коллекция (или куда её нужно сохранить).
 * Затем в бесконечном цикле пользователю предлагается ввести команду,
 * которая сразу же исполняется.
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
