package br.org.restaurantepopular.delivery.config;


import br.org.restaurantepopular.data_db.repository.user.UserDAO;
import br.org.restaurantepopular.data_db.repository.user.UserRepository;
import br.org.restaurantepopular.usecase.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class UserModule {

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(
            IUserRepository userRepository){
        return new GetUserByIdUseCase(userRepository);

    }
    @Bean
    public CreateUserUseCase createUserUseCase(
            IUserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(
            IUserRepository userRepository) {
        return new UpdateUserUseCase(userRepository);
    }

    @Bean
    public ListUsersUserCase listUserUseCase(
            IUserRepository userRepository) {
        return new ListUsersUserCase(userRepository);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(
            IUserRepository userRepository) {
        return new DeleteUserUseCase(userRepository);
    }

    @Bean
    public IUserRepository userRepository(UserDAO userDAO) {
        return new UserRepository(userDAO);
    }


}
