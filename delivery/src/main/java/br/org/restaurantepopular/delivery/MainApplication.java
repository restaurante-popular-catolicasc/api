package br.org.restaurantepopular.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "br.org.restaurantepopular.delivery.config",
                "br.org.restaurantepopular.delivery.rest.impl"
        }
)
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
