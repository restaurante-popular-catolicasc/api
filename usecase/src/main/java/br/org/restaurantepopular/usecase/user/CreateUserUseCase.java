package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.IUseCase;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;

import java.util.Optional;

public class CreateUserUseCase implements IUseCase<User, Optional<User>> {

    private final IUserRepository userRepository;

    public CreateUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> execute(User user){
        if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty())
            throw new ValidationException("User name should not be empty");
        if(user.getRole() == null || user.getRole().isBlank() || user.getRole().isEmpty())
            throw new ValidationException("User Role should not be empty");
        return userRepository.save(user);
    }
}
