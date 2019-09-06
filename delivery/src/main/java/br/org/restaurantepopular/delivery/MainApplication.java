package br.org.restaurantepopular.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
        scanBasePackages = {
                "br.org.restaurantepopular.delivery.config",
                "br.org.restaurantepopular.data_db.config",
                "br.org.restaurantepopular.delivery.rest"
        },
        exclude = RepositoryRestMvcAutoConfiguration.class
)
@ComponentScan(basePackages="br.org.restaurantepopular")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
