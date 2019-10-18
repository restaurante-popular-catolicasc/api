package br.org.restaurantepopular.data_db.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable =  false)
    private String id;
    private String name;
    private String role;
    private int registration;
    private int cpf;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public UserEntity() {

    }

    private UserEntity(String id, String name, String role, int registration, int cpf) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.registration = registration;
        this.cpf = cpf;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getRegistration() {
        return registration;
    }

    public int getCpf() {
        return cpf;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static class Builder {

        private String id;
        private String name;
        private String role;
        private int registration;
        private int cpf;

        public Builder() {

        }

        public UserEntity.Builder id(final String id) {
            this.id = id;
            return this;
        }

        public UserEntity.Builder name(final String name) {
            this.name = name;
            return this;
        }

        public UserEntity.Builder role(final String role) {
            this.role = role;
            return this;
        }

        public UserEntity.Builder registration(final int registration) {
            this.registration = registration;
            return this;
        }

        public UserEntity.Builder cpf(final int cpf) {
            this.cpf = cpf;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, name, role, registration, cpf);
        }
    }
}
