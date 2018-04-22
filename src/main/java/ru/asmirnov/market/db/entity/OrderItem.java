package ru.asmirnov.market.db.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "person_order_item")
public class OrderItem implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_item_order"), nullable = false)
    private PersonOrder order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_order_item_item"), nullable = false)
    private Item item;

    @Column(nullable = false)
    private Long count;

    public OrderItem() {
    }

    public OrderItem(PersonOrder order, Item item, Long count) {
        this.order = order;
        this.item = item;
        this.count = count;
    }

    public PersonOrder getOrder() {
        return order;
    }

    public void setOrder(PersonOrder order) {
        this.order = order;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(item, orderItem.item) &&
                Objects.equals(count, orderItem.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, count);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item +
                ", count=" + count +
                '}';
    }
}
