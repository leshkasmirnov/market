package ru.asmirnov.market.db.entity;

import ru.asmirnov.market.db.entity.common.BaseIntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "item", uniqueConstraints = @UniqueConstraint(name = "uk_code", columnNames = "code"))
public class Item extends BaseIntity {

    @Column(length = 10, nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @OneToMany
    @JoinTable(
            name="item_photo",
            joinColumns = @JoinColumn( name="item_id"),
            inverseJoinColumns = @JoinColumn( name="photo_id"),
            foreignKey = @ForeignKey(name = "fk_item_photo_item"),
            inverseForeignKey = @ForeignKey(name = "fk_item_photo_photo"),
            uniqueConstraints = @UniqueConstraint(name = "uk_photo_id", columnNames = "photo_id")
    )
    private Set<Photo> photos;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_item_item_category"), nullable = false)
    private ItemCategory category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "available_quantity", nullable = false)
    private Long availableQuantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(code, item.code) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(photos, item.photos) &&
                Objects.equals(category, item.category) &&
                Objects.equals(price, item.price) &&
                Objects.equals(availableQuantity, item.availableQuantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), code, name, description, photos, category, price, availableQuantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photos=" + photos +
                ", category=" + category +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", id=" + getId() +
                '}';
    }
}
