package br.org.restaurantepopular.delivery.rest.api.product_type;

import br.org.restaurantepopular.entity.ProductType;

public class ProductTypeMapper {

    public static ProductTypeDTO toProductTypeDTO(ProductType productType) {
        var typeDTO = new ProductTypeDTO();
        
        typeDTO.setId(productType.getId());
        typeDTO.setName(productType.getName());

        return typeDTO;
    }

    public static ProductType toProductType(ProductTypeDTO productTypeDTO) {
        return new ProductType.ProductTypeBuilder()
                .id(productTypeDTO.getId())
                .name(productTypeDTO.getName())
                .build();
    }
}
