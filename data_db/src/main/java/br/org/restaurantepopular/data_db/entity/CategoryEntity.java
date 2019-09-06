package br.org.restaurantepopular.data_db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private String name;
    private String description;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public CategoryEntity() {
    }

    private CategoryEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;

        public Builder() {
        }

        public CategoryEntity.Builder id(final String id) {
            this.id = id;
            return this;
        }

        public CategoryEntity.Builder name(final String name) {
            this.name = name;
            return this;
        }

        public CategoryEntity.Builder description(final String description) {
            this.description = description;
            return this;
        }

        public CategoryEntity build() {
            return new CategoryEntity(id, name, description);
        }
    }
}
