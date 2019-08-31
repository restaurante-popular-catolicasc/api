package br.org.restaurantepopular.delivery.config;

import br.org.restaurantepopular.usecase.core.IUseCaseExecutor;
import br.org.restaurantepopular.usecase.core.UseCaseExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModule {

    @Bean
    public IUseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutor();
    }
}
