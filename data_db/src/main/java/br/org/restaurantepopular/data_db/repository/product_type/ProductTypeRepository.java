package br.org.restaurantepopular.data_db.repository.product_type;

import br.org.restaurantepopular.data_db.mapper.ProductTypeEntityMapper;
import br.org.restaurantepopular.entity.ProductType;
import br.org.restaurantepopular.usecase.product_type.IProductTypeRepository;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;

import  java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductTypeRepository implements IProductTypeRepository {

    private final ProductTypeDAO productTypeDAO;

    public ProductTypeRepository(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @Override
    public void save(ProductType productType) {
        productTypeDAO.save(ProductTypeEntityMapper.toProductTypeEntity(productType));
    }

    @Override
    public void update(ProductType productType) throws NotFoundException {
        if (!productTypeDAO.existsById(productType.getId()))
            throw new NotFoundException(String.format("Not found a product type with this %s ID", productType.getId()));

        productTypeDAO.save(ProductTypeEntityMapper.toProductTypeEntity(productType));
    }

    @Override
    public List<ProductType> listProductTypes() {
        return productTypeDAO
                .findAll()
                .stream()
                .map(ProductTypeEntityMapper::toProductType)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductType> findById(String id) {
        return productTypeDAO
                .findById(id)
                .map(ProductTypeEntityMapper::toProductType);
    }

    @Override
    public void delete(String id) {
        if (!productTypeDAO.existsById(id))
            throw new NotFoundException(String.format("Not found a product type with this %s ID", id));

        productTypeDAO.deleteById(id);
    }
}
