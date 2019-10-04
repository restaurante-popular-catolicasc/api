package br.org.restaurantepopular.data_db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    private String name;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public ProductTypeEntity() {
    }

    private ProductTypeEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static class Builder {

        private String id;
        private String name;

        public Builder() {
        }

        public ProductTypeEntity.Builder id(final String id) {
            this.id = id;
            return this;
        }

        public ProductTypeEntity.Builder name(final String name) {
            this.name = name;
            return this;
        }

        public ProductTypeEntity build() {
            return new ProductTypeEntity(id, name);
        }
    }
}
