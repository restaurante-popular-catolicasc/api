package br.org.restaurantepopular.data_db.repository.user;

import br.org.restaurantepopular.data_db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository <UserEntity, String> { }
