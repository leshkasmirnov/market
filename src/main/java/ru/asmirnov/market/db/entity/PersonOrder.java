package ru.asmirnov.market.db.entity;

import ru.asmirnov.market.common.PayMethod;
import ru.asmirnov.market.db.entity.common.BaseIntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "person_order")
public class PersonOrder extends BaseIntity {

    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "fk_order_person"), nullable = false)
    private Person person;
    @Column(nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private PayMethod payMethod;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonOrder order = (PersonOrder) o;
        return Objects.equals(person, order.person) &&
                Objects.equals(address, order.address) &&
                payMethod == order.payMethod &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), person, address, payMethod, items);
    }

    @Override
    public String toString() {
        return "PersonOrder{" +
                "person=" + person +
                ", address='" + address + '\'' +
                ", payMethod=" + payMethod +
                ", items=" + items +
                ", id=" + getId() +
                '}';
    }
}
