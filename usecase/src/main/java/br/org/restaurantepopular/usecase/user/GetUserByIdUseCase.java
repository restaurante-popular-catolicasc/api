package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class GetUserByIdUseCase implements IUseCase<String, Optional<User>> {

    private final IUserRepository userRepository;

    public GetUserByIdUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> execute(String id) {
        return userRepository.findById(id);
    }
}