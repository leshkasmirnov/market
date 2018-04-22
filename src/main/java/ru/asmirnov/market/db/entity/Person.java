package ru.asmirnov.market.db.entity;

import ru.asmirnov.market.db.entity.common.BaseIntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "person")
public class Person extends BaseIntity {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + getId() +
                '}';
    }
}
