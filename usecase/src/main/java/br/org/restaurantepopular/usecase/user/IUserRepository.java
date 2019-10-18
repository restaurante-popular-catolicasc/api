package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.entity.User;

import java.util.Optional;
import java.util.List;

public interface IUserRepository {

    Optional<User> save(User user);

    void update(User user);

    List<User> listUser();

    Optional<User> findById(String id);

    void delete(String id);
}
