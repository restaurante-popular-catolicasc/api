package br.org.restaurantepopular.data_db.mapper;

import br.org.restaurantepopular.data_db.entity.ProductTypeEntity;
import br.org.restaurantepopular.entity.ProductType;

public class ProductTypeEntityMapper {

    public static ProductTypeEntity toProductTypeEntity(ProductType productType) {
        return new ProductTypeEntity.Builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }

    public static ProductType toProductType(ProductTypeEntity productTypeEntity) {
        return new ProductType.ProductTypeBuilder()
                .id(productTypeEntity.getId())
                .name(productTypeEntity.getName())
                .build();

    }
}
