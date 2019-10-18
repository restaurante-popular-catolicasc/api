package br.org.restaurantepopular.data_db.mapper;

import br.org.restaurantepopular.data_db.entity.UserEntity;
import br.org.restaurantepopular.entity.User;

public class UserEntityMapper {
    public static UserEntity toUserEntity(User user){
        return new UserEntity.Builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .registration(user.getRegistration())
                .cpf(user.getCpf())
                .build();
    }

    public static User toUser(UserEntity userEntity) {
        return new User.UserBuilder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .registration(userEntity.getRegistration())
                .cpf(userEntity.getCpf())
                .build();
    }
}
