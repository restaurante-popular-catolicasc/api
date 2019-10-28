package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.core.IUseCase
import br.org.restaurantepopular.usecase.core.exception.ValidationException

class CreateUserUseCase(
    private val userRepository: IUserRepository
) : IUseCase<User, User> {

    override fun execute(request: User): User {
        if (request.name.isEmpty())
            throw ValidationException("User name should not be empty")
        if (request.role.isNullOrEmpty())
            throw ValidationException("User Role should not be empty")
        return userRepository.save(request)
    }
}
