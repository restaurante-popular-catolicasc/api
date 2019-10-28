package br.org.restaurantepopular.usecase.product_type;

import br.org.restaurantepopular.entity.ProductType;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.List;
import java.util.Optional;

public class ListProductTypesUseCase implements IUseCase<Optional, List<ProductType>> {

    private final IProductTypeRepository productTypeRepository;

    public ListProductTypesUseCase(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductType> execute(Optional optional) {
        return productTypeRepository.listProductTypes();
    }
}
