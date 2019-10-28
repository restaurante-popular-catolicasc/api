package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.usecase.core.IUseCase

class DeleteUserUseCase(
    private val userRepository: IUserRepository
) : IUseCase<String, Unit> {

    override fun execute(request: String) {
        userRepository.delete(request)
    }
}