package br.org.restaurantepopular.data_db.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["br.org.restaurantepopular.data_db.entity"])
@EnableJpaRepositories(basePackages = ["br.org.restaurantepopular.data_db.repository"])
class DBConfig