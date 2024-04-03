package se.ifmo.core.collection.objects;

import se.ifmo.core.collection.exceptions.InvalidArgumentException;
import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.util.Date;
import java.util.Objects;

public class HumanBeing implements Comparable<HumanBeing> {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) throw new NullableFieldException("id");
        if (id <= 0) throw new InvalidArgumentException("id", "Значение поля должно быть больше 0");
        this.id = id;
    }

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        if (name.isBlank() || name.isEmpty()) throw new InvalidArgumentException("name", "Строка не может быть пустой");
        this.name = name;
    }

    /**
     * Поле не может быть null
     */
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new NullableFieldException("coordinates");
        this.coordinates = coordinates;
    }

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    private Date creationDate = new Date();

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) throw new NullableFieldException("creationDate");
        this.creationDate = creationDate;
    }

    private boolean realHero;

    public boolean isRealHero() {
        return realHero;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    /**
     * Поле не может быть null
     */
    private Boolean hasToothpick;

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(Boolean hasToothpick) {
        if (hasToothpick == null) throw new NullableFieldException("hasToothpick");
        this.hasToothpick = hasToothpick;
    }

    /**
     * Значение поля должно быть больше -953
     */
    private long impactSpeed;

    public long getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(long impactSpeed) {
        if (impactSpeed <= -953)
            throw new InvalidArgumentException("impactSpeed", "Значение поля должно быть больше -953");
        this.impactSpeed = impactSpeed;
    }

    /**
     * Поле не может быть null
     */
    private String soundtrackName;

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setSoundtrackName(String soundtrackName) {
        if (soundtrackName == null) throw new NullableFieldException("soundtrackName");
        this.soundtrackName = soundtrackName;
    }

    private int minutesOfWaiting;

    public int getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(int minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    /**
     * Поле может быть null
     */
    private Mood mood;

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * Поле не может быть null
     */
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if (car == null) throw new NullableFieldException("car");
        this.car = car;
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", soundtrackName='" + soundtrackName + '\'' +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", mood=" + mood +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return realHero == that.realHero && impactSpeed == that.impactSpeed && minutesOfWaiting == that.minutesOfWaiting && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(hasToothpick, that.hasToothpick) && Objects.equals(soundtrackName, that.soundtrackName) && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);
    }

    @Override
    public int compareTo(HumanBeing o) {
        return id.compareTo(o.id) + name.compareTo(o.name) + coordinates.compareTo(o.coordinates) + creationDate.compareTo(o.creationDate) + Boolean.compare(realHero, o.realHero) + hasToothpick.compareTo(o.hasToothpick) + Long.compare(impactSpeed, o.impactSpeed) + soundtrackName.compareTo(o.soundtrackName) + Integer.compare(minutesOfWaiting, o.minutesOfWaiting) + mood.compareTo(o.mood) + car.compareTo(o.car);
    }
}
