package br.org.restaurantepopular.delivery.rest.api.user;

import br.org.restaurantepopular.entity.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        var userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        userDTO.setCpf(user.getCpf());
        userDTO.setRegistration(user.getRegistration());

        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        return new User.UserBuilder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .role(userDTO.getRole())
                .cpf(userDTO.getCpf())
                .registration(userDTO.getRegistration())
                .build();

    }
}
