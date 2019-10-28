package br.org.restaurantepopular.delivery.rest.api.user

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/users")
interface IUserResource {

    @GetMapping(
        value = ["{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getUserById(@PathVariable("id") id: String): CompletionStage<UserDTO>

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody userDTO: UserDTO): CompletionStage<*>

    @PutMapping(
        value = ["{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateUser(
        @PathVariable("id") id: String,
        @RequestBody userDTO: UserDTO
    ): CompletionStage<ResponseEntity<Unit>>

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun listUsers(): CompletionStage<List<UserDTO>>

    @DeleteMapping(value = ["{id}"])
    fun deleteUser(@PathVariable("id") id: String): CompletionStage<ResponseEntity<Unit>>
}
