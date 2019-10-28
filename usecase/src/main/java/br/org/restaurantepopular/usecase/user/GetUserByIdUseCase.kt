package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.core.IUseCase

class GetUserByIdUseCase(
    private val userRepository: IUserRepository
) : IUseCase<String, User> {

    override fun execute(request: String): User {
        return userRepository.findById(request)
    }
}