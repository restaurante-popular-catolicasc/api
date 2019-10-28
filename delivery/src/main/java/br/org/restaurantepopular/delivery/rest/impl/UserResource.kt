package br.org.restaurantepopular.delivery.rest.impl

import br.org.restaurantepopular.delivery.rest.api.user.*
import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor
import br.org.restaurantepopular.usecase.user.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletionStage

@RestController
class UserResource @Autowired
constructor(
    private val useCaseExecutor: IUseCaseExecutor,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val listUsersUseCase: ListUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : IUserResource {

    override fun getUserById(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = getUserByIdUseCase,
        requestDto = id,
        requestConverter = { id },
        responseConverter = { it.toUserDTO() }
    )

    override fun createUser(
        @RequestBody userDTO: UserDTO
    ) =
        useCaseExecutor(
            useCase = createUserUseCase,
            requestDto = userDTO,
            requestConverter = { it.toUser() },
            responseConverter = { ResponseEntity(it.toUserDTO(), HttpStatus.CREATED) }
        )

    override fun updateUser(
        @PathVariable("id") id: String,
        @RequestBody userDTO: UserDTO
    ) = useCaseExecutor(
        useCase = updateUserUseCase,
        requestDto = userDTO.apply { this.id = id },
        requestConverter = { it.toUser() },
        responseConverter = { ResponseEntity<Unit>(HttpStatus.OK) }
    )

    override fun listUsers(): CompletionStage<List<UserDTO>> {
        return useCaseExecutor(
            useCase = listUsersUseCase,
            responseConverter = { it.map(User::toUserDTO) }
        )
    }

    override fun deleteUser(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = deleteUserUseCase,
        requestDto = id,
        requestConverter = { requestDTO: String -> requestDTO },
        responseConverter = { ResponseEntity<Unit>(HttpStatus.NO_CONTENT) }
    )
}
