package ru.asmirnov.market.db.entity;

import ru.asmirnov.market.db.entity.common.BaseIntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "item_category")
public class ItemCategory extends BaseIntity {

    @Column(nullable = false)
    private String name;
    private String description;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_item_category_photo"))
    private Photo photo;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_item_category_parent"))
    private ItemCategory parent;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;

    @OneToMany(mappedBy = "parent")
    private Set<ItemCategory> childs;

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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public ItemCategory getParent() {
        return parent;
    }

    public void setParent(ItemCategory parent) {
        this.parent = parent;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<ItemCategory> getChilds() {
        return childs;
    }

    public void setChilds(Set<ItemCategory> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo=" + photo +
                ", childs=" + childs +
                ", id=" + getId() +
                '}';
    }
}
