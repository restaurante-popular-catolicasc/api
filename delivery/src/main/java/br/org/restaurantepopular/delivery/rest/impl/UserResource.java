package br.org.restaurantepopular.delivery.rest.impl;

import br.org.restaurantepopular.delivery.rest.api.user.IUserResource;
import br.org.restaurantepopular.delivery.rest.api.user.UserDTO;
import br.org.restaurantepopular.delivery.rest.api.user.UserMapper;
import br.org.restaurantepopular.entity.User;
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor;
import br.org.restaurantepopular.usecase.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
public class UserResource implements IUserResource {
    private final IUseCaseExecutor useCaseExecutor;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ListUsersUserCase listUsersUserCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Autowired
    public UserResource(IUseCaseExecutor useCaseExecutor, GetUserByIdUseCase getUserByIdUseCase, CreateUserUseCase createUserUseCase, UpdateUserUseCase updateUserUseCase, ListUsersUserCase listUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.listUsersUserCase = listUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public CompletionStage<UserDTO> getUserById(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                getUserByIdUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional<User> user) ->
                        user.map(UserMapper::toUserDTO).get()
        );
    }


    @Override
    public CompletionStage<ResponseEntity<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        return useCaseExecutor.invoke(
                createUserUseCase,
                userDTO,
                UserMapper::toUser,
                (Optional<User> user) ->
                        new ResponseEntity<>(
                                user.map(UserMapper::toUserDTO).get(),
                                HttpStatus.CREATED
                        )
        );
    }

    @Override
    public CompletionStage<ResponseEntity> updateUser(@RequestBody UserDTO userDTO) {
        return useCaseExecutor.invoke(
                updateUserUseCase,
                userDTO,
                UserMapper::toUser,
                (Optional v) -> new ResponseEntity(HttpStatus.OK)
        );
    }

    @Override
    public CompletionStage<List<UserDTO>> listUsers() {
        return useCaseExecutor.invoke(
                listUsersUserCase,
                (List<User> users) ->
                        users
                                .stream()
                                .map(UserMapper::toUserDTO)
                                .collect(Collectors.toList())
        );
    }

    @Override
    public CompletionStage<ResponseEntity> deleteUser(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                deleteUserUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional v) -> new ResponseEntity(HttpStatus.NO_CONTENT)
        );
    }


}
