package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.List;
import java.util.Optional;

public class ListUsersUserCase implements IUseCase<Optional, List<User>> {

    private final IUserRepository userRepository;

    public ListUsersUserCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute(Optional optional) {
        return userRepository.listUser();
    }



}
