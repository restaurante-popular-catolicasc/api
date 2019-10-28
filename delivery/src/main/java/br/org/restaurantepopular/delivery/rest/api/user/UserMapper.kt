package br.org.restaurantepopular.delivery.rest.api.user

import br.org.restaurantepopular.entity.User

fun User.toUserDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        name = this.name,
        role = this.role,
        cpf = this.cpf,
        code = this.code
    )
}

fun UserDTO.toUser(): User {
    return User(
        id = this.id,
        name = this.name ?: "",
        role = this.role ?: "",
        code = this.code ?: "",
        cpf = this.cpf ?: ""
    )
}

