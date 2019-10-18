package br.org.restaurantepopular.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.restaurantepopular.data_db.repository.product_type.*;
import br.org.restaurantepopular.usecase.product_type.*;

@Configuration
public class ProductTypeModule {

    @Bean
    public GetProductTypeByIdUseCase getProductTypeByIdUseCase(
            IProductTypeRepository productTypeRepository) {
        return new GetProductTypeByIdUseCase(productTypeRepository);
    }

    @Bean
    public CreateProductTypeUseCase createProductTypeUseCase(
            IProductTypeRepository productTypeRepository) {
        return new CreateProductTypeUseCase(productTypeRepository);
    }

    @Bean
    public UpdateProductTypeUseCase updateProductTypeUseCase(
            IProductTypeRepository productTypeRepository) {
        return new UpdateProductTypeUseCase(productTypeRepository);
    }

    @Bean
    public ListProductTypesUseCase listProductTypesUseCase(
            IProductTypeRepository productTypeRepository) {
        return new ListProductTypesUseCase(productTypeRepository);
    }

    @Bean
    public DeleteProductTypeUseCase deleteProductTypeUseCase(
            IProductTypeRepository productTypeRepository) {
        return new DeleteProductTypeUseCase(productTypeRepository);
    }

    @Bean
    public IProductTypeRepository productTypeRepository(ProductTypeDAO productTypeDAO) {
        return new ProductTypeRepository(productTypeDAO);
    }
}

