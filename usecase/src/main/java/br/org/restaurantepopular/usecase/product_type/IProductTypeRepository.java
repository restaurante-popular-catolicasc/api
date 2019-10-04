package br.org.restaurantepopular.usecase.product_type;

import br.org.restaurantepopular.entity.ProductType;

import java.util.List;
import java.util.Optional;

public interface IProductTypeRepository {

    void save(ProductType productType);

    void update(ProductType productType);

    List<ProductType> listProductTypes();

    Optional<ProductType> findById(String id);

    void delete(String id);
}
