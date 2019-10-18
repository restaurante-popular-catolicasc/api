package br.org.restaurantepopular.usecase.user;

import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class DeleteUserUseCase implements IUseCase<String, Optional> {

    private final IUserRepository userRepository;

    public DeleteUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional execute(String s) {
        userRepository.delete(s);
        return Optional.empty();
    }
}
