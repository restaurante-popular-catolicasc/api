package br.org.restaurantepopular.data_db.repository.user

import br.org.restaurantepopular.data_db.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserDAO : JpaRepository<UserEntity, String>