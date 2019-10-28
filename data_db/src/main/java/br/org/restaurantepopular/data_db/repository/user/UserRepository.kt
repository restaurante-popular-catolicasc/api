package br.org.restaurantepopular.data_db.repository.user

import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import br.org.restaurantepopular.data_db.mapper.UserEntityMapper
import br.org.restaurantepopular.entity.User
import br.org.restaurantepopular.usecase.user.IUserRepository

class UserRepository(private val userDAO: UserDAO) : IUserRepository {

    override fun save(user: User): User {
        return UserEntityMapper.toUser(
            userDAO.save(UserEntityMapper.toUserEntity(user))
        )
    }

    @Throws(NotFoundException::class)
    override fun update(user: User) {
        if (!userDAO.existsById(user.id!!))
            throw NotFoundException(String.format("Not found a user with this %s ID", user.id))

        userDAO.save(UserEntityMapper.toUserEntity(user))
    }

    override fun listUser(): List<User> {
        return userDAO
            .findAll()
            .map(UserEntityMapper::toUser)
    }

    @Throws(NotFoundException::class)
    override fun findById(id: String): User {
        val user = userDAO.findById(id).map(UserEntityMapper::toUser)
        if (user.isEmpty)
            throw NotFoundException(String.format("Not found a user with this %s ID", id))
        return user.get()
    }

    override fun delete(id: String) {
        if (!userDAO.existsById(id))
            throw NotFoundException(String.format("Not found a user with this %s ID", id))
        userDAO.deleteById(id)
    }
}