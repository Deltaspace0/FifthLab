package ConsoleAPP.parameters;

import ConsoleAPP.CSVStringBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Worker implements Comparable<Worker> {
    private long ID; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int salary; //Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле может быть null
    private Person person; //Поле может быть null

    private String infoString;

    public Worker() {

    }

    public Worker(String line) {
        ArrayList<String> tokens = CSVStringBuilder.parse(line);
        ID = Long.parseLong(tokens.get(0));
        name = tokens.get(1);
        coordinates = new Coordinates();
        coordinates.setX(Integer.parseInt(tokens.get(2)));
        coordinates.setY(Double.parseDouble(tokens.get(3)));
        creationDate = ZonedDateTime.of(LocalDateTime.parse(tokens.get(4), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm")), ZoneId.systemDefault());
        salary = Integer.parseInt(tokens.get(5));
        position = EnumFieldParsers.parsePosition(tokens.get(6));
        status = EnumFieldParsers.parseStatus(tokens.get(7));
        if (!tokens.get(8).equals("null")) {
            person = new Person();
            person.setBirthday(LocalDateTime.parse(tokens.get(8), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm")));
            person.setHairColor(EnumFieldParsers.parseColor(tokens.get(9)));
            if (!tokens.get(10).equals("null")) {
                Location location = new Location();
                location.setName(tokens.get(10));
                location.setX(Long.valueOf(tokens.get(11)));
                location.setY(Integer.valueOf(tokens.get(12)));
                location.setZ(Integer.valueOf(tokens.get(13)));
                person.setLocation(location);
            }
        }
        generateInfoString();
    }

    public int compareTo(Worker anotherWorker) {
        return salary - anotherWorker.getSalary();
    }

    public String toString() {
        CSVStringBuilder csvStringBuilder = new CSVStringBuilder();
        csvStringBuilder.append(String.valueOf(ID));
        csvStringBuilder.append(name);
        csvStringBuilder.append(String.valueOf(coordinates.getX()));
        csvStringBuilder.append(String.valueOf(coordinates.getY()));
        csvStringBuilder.append(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm").format(creationDate));
        csvStringBuilder.append(String.valueOf(salary));
        csvStringBuilder.append(position.name());
        csvStringBuilder.append(status == null ? "null" : status.name());
        if (person == null) {
            for (int i = 0; i < 6; i++)
                csvStringBuilder.append("null");
        } else {
            csvStringBuilder.append(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm").format(person.getBirthday()));
            csvStringBuilder.append(person.getHairColor().name());
            if (person.getLocation() == null) {
                for (int i = 0; i < 4; i++)
                    csvStringBuilder.append("null");
            } else {
                csvStringBuilder.append(person.getLocation().getName());
                csvStringBuilder.append(String.valueOf(person.getLocation().getX()));
                csvStringBuilder.append(String.valueOf(person.getLocation().getY()));
                csvStringBuilder.append(String.valueOf(person.getLocation().getZ()));
            }
        }
        return csvStringBuilder.line;
    }

    public void generateInfoString() {
        infoString = "\nID: " + ID +
                "\nИмя сотрудника: " + name +
                "\nКоординаты:" +
                "\n\tX: " + coordinates.getX() +
                "\n\tY: " + coordinates.getY() +
                "\nВремя создания: " + DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy").format(creationDate) +
                "\nЗарплата: " + salary +
                "\nДолжность: " + position +
                "\nСтатус: " + (status == null ? "null" : status) +
                "\nПерсональные данные: " + (person == null ? "null" :
                "\n\tДата рождения: " + DateTimeFormatter.ofPattern("dd.MM.yyyy").format(person.getBirthday()) +
                "\n\tЦвет волос: " + person.getHairColor() +
                "\n\tМесто работы: " + (person.getLocation() == null ? "null" :
                "\n\t\tНазвание: " + person.getLocation().getName() +
                "\n\t\tX: " + person.getLocation().getX() +
                "\n\t\tY: " + person.getLocation().getY() +
                "\n\t\tZ: " + person.getLocation().getZ())) + "\n";
    }

    public String getInfoString() {
        return infoString;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
