package br.org.restaurantepopular.delivery

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(
    scanBasePackages =
    ["br.org.restaurantepopular.delivery.config",
        "br.org.restaurantepopular.data_db.config",
        "br.org.restaurantepopular.delivery.rest"
    ],
    exclude = [RepositoryRestMvcAutoConfiguration::class]
)
@ComponentScan(basePackages = ["br.org.restaurantepopular"])
class MainApplication

fun main(args: Array<String>) {
    SpringApplication.run(MainApplication::class.java, *args)
}
