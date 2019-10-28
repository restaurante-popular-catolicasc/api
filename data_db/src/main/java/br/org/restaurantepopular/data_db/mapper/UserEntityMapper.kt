package br.org.restaurantepopular.data_db.mapper

import br.org.restaurantepopular.data_db.entity.UserEntity
import br.org.restaurantepopular.entity.User

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id ?: "",
        name = this.name,
        role = this.role,
        code = this.code,
        cpf = this.cpf
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        role = this.role,
        code = this.code,
        cpf = this.cpf
    )
}
