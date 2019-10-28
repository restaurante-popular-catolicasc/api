package br.org.restaurantepopular.delivery.config

import br.org.restaurantepopular.usecase.core.UseCaseExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppModule {
    @Bean
    fun useCaseExecutor() = UseCaseExecutor()
}