package ru.asmirnov.market.db.entity;

import ru.asmirnov.market.db.entity.common.BaseIntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Entity
@Table(name = "photo")
public class Photo extends BaseIntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer size;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Photo photo = (Photo) o;
        return Objects.equals(description, photo.description) &&
                Objects.equals(size, photo.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), description, size);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "description='" + description + '\'' +
                ", size=" + size +
                ", id=" + getId() +
                '}';
    }
}
