package br.org.restaurantepopular.data_db.mapper

import br.org.restaurantepopular.data_db.entity.UserEntity
import br.org.restaurantepopular.entity.User

object UserEntityMapper {
    fun toUserEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id ?: "",
            name = user.name,
            role = user.role,
            code = user.code,
            cpf = user.cpf
        )
    }

    fun toUser(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            name = userEntity.name,
            role = userEntity.role,
            code = userEntity.code,
            cpf = userEntity.cpf
        )
    }
}