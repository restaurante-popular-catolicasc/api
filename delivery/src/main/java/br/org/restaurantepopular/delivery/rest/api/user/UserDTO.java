package br.org.restaurantepopular.delivery.rest.api.user;

public class UserDTO {

    private String id;
    private String name;
    private String role;
    private int registration;
    private int cpf;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

}
