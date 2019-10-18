package br.org.restaurantepopular.entity;

import java.util.Objects;

public class User {

    private String id;
    private String name;
    private String role;
    private int registration;
    private int cpf;

    public User(String id, String name, String role, int registration, int cpf) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.registration = registration;
        this.cpf = cpf;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public static class UserBuilder {

        private String id;
        private String name;
        private String role;
        private int registration;
        private int cpf;

        public UserBuilder(){
        }

        public UserBuilder id(final String id){
            this.id = id;
            return this;
        }

        public UserBuilder name(final String name){
            this.name = name;
            return this;
        }

        public UserBuilder role(final String role){
            this.role = role;
            return this;
        }

        public UserBuilder registration(final int registration){
            this.registration = registration;
            return this;
        }

        public UserBuilder cpf(final int cpf){
            this.cpf = cpf;
            return this;
        }

        public User build() {
            return new User(id, name, role, registration, cpf);
        }
    }

    @Override
    public String toString(){
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", registration='" + registration + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getRegistration(), user.getRegistration()) &&
                Objects.equals(getCpf(), user.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole(), getRegistration(), getCpf());
    }

}
