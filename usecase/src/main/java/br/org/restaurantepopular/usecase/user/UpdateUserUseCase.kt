package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.core.IUseCase

class UpdateUserUseCase(
    private val userRepository: IUserRepository
) : IUseCase<User, Unit> {

    override fun execute(request: User){
        userRepository.update(request)
    }
}