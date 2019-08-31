package br.org.restaurantepopular.data_db.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"br.org.restaurantepopular.data_db.jpa.entity"})
@EnableJpaRepositories(basePackages = {"br.org.restaurantepopular.data_db.jpa.repository"})
public class DBConfig {
}