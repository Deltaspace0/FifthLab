package ConsoleAPP;

import ConsoleAPP.exceptions.*;
import ConsoleAPP.parameters.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Это менеджер коллекции. Он умеет удалять элементы.
 * Он умеет загружать коллекцию из файла и сохранять её
 * через FileProcessor (работу с файлами я выделил в
 * отдельный класс, так как ExecuteScript тоже хочет читать,
 * а там получается очень похожий код). Также менеджер тянет все
 * InputProvider'ы, чтобы создавать новые элементы.
 */

public class CollectionManager {
    public final LinkedHashSet<Worker> elements = new LinkedHashSet<>();
    public final HashMap<Long, Worker> workersByID = new HashMap<>();
    private final String filePath;
    private LocalDateTime initializationDate;
    private long nextID;

    public CollectionManager(String filePath) {
        this.filePath = filePath;
        boolean createNew = false;
        try {
            String[] lines = FileProcessor.readFromFile(filePath).split("\\r?\\n");
            String[] meta = lines[0].split(",");
            initializationDate = LocalDateTime.parse(meta[0], DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm"));
            nextID = Long.parseLong(meta[1]);
            for (int i = 1; i < lines.length; i++) {
                Worker worker;
                try {
                    worker = new Worker(lines[i]);
                } catch (InputException exception) {
                    System.out.println("При загрузке работника возникла ошибка: " + exception.getMessage());
                    continue;
                }
                elements.add(worker);
                workersByID.put(worker.getID(), worker);
            }
        } catch (FileDoesNotExistException exception) {
            System.out.println("Внимание! Файла не было, поэтому вот новая коллекция.");
            createNew = true;
        } catch (InputException exception) {
            System.out.println(exception.getMessage());
            createNew = true;
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            System.out.println("\nКороче файл попорчен, поэтому вот новая коллекция.");
            createNew = true;
        }
        if (createNew) {
            initializationDate = LocalDateTime.now();
            clear();
        }
    }

    /**
     * Используется командой Info
     *
     * @return
     */

    public String getInfo() {
        return "Тип коллекции: LinkedHashSet" +
                "\nДата инициализации: " + DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy").format(initializationDate) +
                "\nКоличество элементов: " + elements.size() +
                "\nЗагружена из файла: " + filePath +
                "\nСледующий элемент будет иметь ID " + nextID;
    }

    /**
     * Используется командой Add
     * <p>
     * Сначала все провайдеры тянулись сюда, но нужен был
     * отдельный метод для обновления элемента, а
     * делать дупликат кода было плохой идеей. Поэтому
     * я перенёс всё туда, а этот метод теперь просто создаёт
     * голый элемент с одной датой создания и ID, а затем
     * начинает процедуру обновления элемента.
     *
     * @return
     */

    public long initiateElementAddingProcedure() {
        while (workersByID.containsKey(nextID))
            nextID++;
        Worker worker = new Worker();
        worker.setID(nextID);
        worker.setCreationDate(ZonedDateTime.now());
        elements.add(worker);
        workersByID.put(nextID, worker);
        initiateElementUpdatingProcedure(nextID);
        return nextID++;
    }

    /**
     * Используется командой Update
     * <p>
     * InputProvider'ы в действии. Позволяют просто использовать их как значения и
     * берут всю ответственность за взаимодействие с пользователем на себя, что весьма удобно.
     *
     * @param ID
     * @return
     */

    public boolean initiateElementUpdatingProcedure(long ID) {
        Worker worker = workersByID.get(ID);
        if (worker == null)
            return false;
        worker.setName(FieldInputProviders.workerNameInputProvider.provide());
        Coordinates coordinates = new Coordinates();
        coordinates.setX(FieldInputProviders.coordinatesXInputProvider.provide());
        coordinates.setY(FieldInputProviders.coordinatesYInputProvider.provide());
        worker.setCoordinates(coordinates);
        worker.setSalary(FieldInputProviders.salaryInputProvider.provide());
        worker.setPosition(FieldInputProviders.positionInputProvider.provide());
        if (FieldInputProviders.statusConfirmationInputProvider.provide())
            worker.setStatus(FieldInputProviders.statusInputProvider.provide());
        if (FieldInputProviders.personConfirmationInputProvider.provide()) {
            Person person = new Person();
            person.setBirthday(FieldInputProviders.birthdayInputProvider.provide());
            person.setHairColor(FieldInputProviders.colorInputProvider.provide());
            if (FieldInputProviders.locationConfirmationInputProvider.provide()) {
                Location location = new Location();
                location.setName(FieldInputProviders.locationNameInputProvider.provide());
                location.setX(FieldInputProviders.locationXInputProvider.provide());
                location.setY(FieldInputProviders.locationYInputProvider.provide());
                location.setZ(FieldInputProviders.locationZInputProvider.provide());
                person.setLocation(location);
            }
            worker.setPerson(person);
        }
        worker.generateInfoString();
        return true;
    }

    /**
     * Используется командой RemoveByID
     *
     * @param ID
     * @return
     */

    public boolean removeElement(long ID) {
        Worker worker = workersByID.get(ID);
        if (worker == null)
            return false;
        elements.remove(worker);
        workersByID.remove(ID);
        if (ID == nextID-1)
            nextID--; // позволяет повторно использовать освободившиеся ID
        return true;
    }

    /**
     * Используется командой Clear
     *
     * Происходит полный сброс всей коллекции.
     */

    public void clear() {
        elements.clear();
        workersByID.clear();
        nextID = 1;
    }

    /**
     * Используется командой Save
     * <p>
     * Вверху CSV таблицы пишет время создания коллекции и
     * ID следующего нового элемента (чтобы не приходилось при
     * каждой загрузке проходиться по всему списку элементов для
     * нахождения свободного ID)
     *
     * @throws InputException
     */

    public void saveToFile() throws InputException {
        StringBuilder csv = new StringBuilder();
        csv.append(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm").format(initializationDate));
        csv.append(",").append(nextID).append(",,,,,,,,,,,,\n"); // Запятых ровно столько, чтобы первая строчка имела с остальными одинаковое количество столбцов
        for (Worker worker : elements)
            csv.append(worker.toString()).append("\n");
        FileProcessor.saveToFile(filePath, csv.toString().trim());
    }
}
