package br.org.restaurantepopular.usecase.product_type;

import br.org.restaurantepopular.entity.ProductType;
import br.org.restaurantepopular.usecase.core.IUseCase;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;

import java.util.Optional;

public class CreateProductTypeUseCase implements IUseCase<ProductType, Optional> {

    private final IProductTypeRepository productTypeRepository;

    public CreateProductTypeUseCase(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public Optional execute(ProductType productType) {
        if (productType.getName() == null || productType.getName().isBlank() || productType.getName().isEmpty())
            throw new ValidationException("Product type name should not be empty");
            productTypeRepository.save(productType);
        return Optional.empty();
    }
}
