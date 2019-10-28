package br.org.restaurantepopular.delivery.config

import br.org.restaurantepopular.data_db.repository.user.UserDAO
import br.org.restaurantepopular.data_db.repository.user.UserRepository
import br.org.restaurantepopular.usecase.user.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserModule {

    @Bean
    fun getUserByIdUseCase(userRepository: IUserRepository) = GetUserByIdUseCase(userRepository)

    @Bean
    fun createUserUseCase(userRepository: IUserRepository) = CreateUserUseCase(userRepository)

    @Bean
    fun updateUserUseCase(userRepository: IUserRepository) = UpdateUserUseCase(userRepository)

    @Bean
    fun listUserUseCase(userRepository: IUserRepository) = ListUsersUseCase(userRepository)

    @Bean
    fun deleteUserUseCase(userRepository: IUserRepository) = DeleteUserUseCase(userRepository)

    @Bean
    fun userRepository(userDAO: UserDAO) = UserRepository(userDAO)

}
