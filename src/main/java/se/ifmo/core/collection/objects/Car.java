package se.ifmo.core.collection.objects;

import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.util.Objects;

public class Car implements Comparable<Car> {
    /**
     * Поле не может быть null
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        this.name = name;
    }

    /**
     * Поле может быть null
     */
    private Boolean cool;

    public Boolean getCool() {
        return cool;
    }

    public void setCool(Boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(cool, car.cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }

    @Override
    public int compareTo(Car o) {
        return name.compareTo(o.name) + cool.compareTo(o.cool);
    }
}
