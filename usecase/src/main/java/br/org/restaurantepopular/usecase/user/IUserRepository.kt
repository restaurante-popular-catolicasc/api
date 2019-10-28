package br.org.restaurantepopular.usecase.user

import br.org.restaurantepopular.entity.User

interface IUserRepository {

    fun save(user: User): User
    fun update(user: User)
    fun listUser(): List<User>
    fun findById(id: String): User
    fun delete(id: String)
}