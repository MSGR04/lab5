package se.ifmo.core.collection.objects;

import se.ifmo.core.collection.exceptions.InvalidArgumentException;
import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    /**
     * Максимальное значение поля: 844
     * Поле не может быть null
     */
    private Integer x;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        if (x == null) throw new NullableFieldException("x");
        if (x > 844) throw new InvalidArgumentException("x", "Максимальное значение поля: 844");
        this.x = x;
    }

    private float y;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(y, that.y) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.x) + Float.compare(y, o.y);
    }
}
