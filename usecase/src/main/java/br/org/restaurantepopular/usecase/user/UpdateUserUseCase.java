package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class UpdateUserUseCase implements IUseCase<User, Optional> {

    private final IUserRepository userRepository;

    public UpdateUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional execute(User user) {

        userRepository.update(user);
        return Optional.empty();
    }
}
