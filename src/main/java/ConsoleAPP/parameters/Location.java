package ConsoleAPP.parameters;

/**
 * Место работы. Здесь есть поля для названия места работы,
 * а также его положений во всех трёх измерениях.
 */

public class Location {
    private Long x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
