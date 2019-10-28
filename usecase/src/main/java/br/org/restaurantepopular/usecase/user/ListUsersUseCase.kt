package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.core.IUseCase

class ListUsersUseCase(
    private val userRepository: IUserRepository
) : IUseCase<Unit, List<User>> {

    override fun execute(request: Unit): List<User> {
        return userRepository.listUser()
    }
}