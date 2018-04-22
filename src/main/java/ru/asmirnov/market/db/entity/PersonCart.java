package ru.asmirnov.market.db.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "person_cart")
public class PersonCart implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "fk_person_cart_person"))
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_person_cart_item"))
    private Item item;

    @Column(nullable = false)
    private Long count;

    public PersonCart() {
    }

    public PersonCart(Person person, Item item, Long count) {
        this.person = person;
        this.item = item;
        this.count = count;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
