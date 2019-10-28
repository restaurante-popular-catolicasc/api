package br.org.restaurantepopular.delivery.rest.impl;

import br.org.restaurantepopular.delivery.rest.api.product_type.ProductTypeDTO;
import br.org.restaurantepopular.delivery.rest.api.product_type.ProductTypeMapper;
import br.org.restaurantepopular.delivery.rest.api.product_type.IProductTypeResource;
import br.org.restaurantepopular.entity.ProductType;
import br.org.restaurantepopular.usecase.product_type.*;
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor;
import br.org.restaurantepopular.usecase.product_type.CreateProductTypeUseCase;
import br.org.restaurantepopular.usecase.product_type.DeleteProductTypeUseCase;
import br.org.restaurantepopular.usecase.product_type.GetProductTypeByIdUseCase;
import br.org.restaurantepopular.usecase.product_type.UpdateProductTypeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
public class ProductTypeResource implements IProductTypeResource {

    private final IUseCaseExecutor useCaseExecutor;
    private final GetProductTypeByIdUseCase getProductTypeByIdUseCase;
    private final CreateProductTypeUseCase createProductTypeUseCase;
    private final UpdateProductTypeUseCase updateProductTypeUseCase;
    private final ListProductTypesUseCase listProductTypesUseCase;
    private final DeleteProductTypeUseCase deleteProductTypeUseCase;

    @Autowired
    public ProductTypeResource(IUseCaseExecutor useCaseExecutor, GetProductTypeByIdUseCase getProductTypeByIdUseCase, CreateProductTypeUseCase createProductTypeUseCase, UpdateProductTypeUseCase updateProductTypeUseCase, ListProductTypesUseCase listProductTypesUseCase, DeleteProductTypeUseCase deleteProductTypeUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getProductTypeByIdUseCase = getProductTypeByIdUseCase;
        this.createProductTypeUseCase = createProductTypeUseCase;
        this.updateProductTypeUseCase = updateProductTypeUseCase;
        this.listProductTypesUseCase = listProductTypesUseCase;
        this.deleteProductTypeUseCase = deleteProductTypeUseCase;
    }

    @Override
    public CompletionStage<ProductTypeDTO> getProductTypeById(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                getProductTypeByIdUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional<ProductType> productType) ->
                        productType.map(ProductTypeMapper::toProductTypeDTO).get()
        );
    }

    @Override
    public CompletionStage<ResponseEntity> createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return useCaseExecutor.invoke(
                createProductTypeUseCase,
                productTypeDTO,
                ProductTypeMapper::toProductType,
                (Optional<ProductType> productType) ->
                        new ResponseEntity<>(
                                productType.map(ProductTypeMapper::toProductTypeDTO).get(),
                                HttpStatus.CREATED
                        )
        );
    }

    @Override
    public CompletionStage<ResponseEntity> updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return useCaseExecutor.invoke(
                updateProductTypeUseCase,
                productTypeDTO,
                ProductTypeMapper::toProductType,
                (Optional v) -> new ResponseEntity(HttpStatus.OK)
        );
    }

    @Override
    public CompletionStage<List<ProductTypeDTO>> listProductTypes() {
        return useCaseExecutor.invoke(
                listProductTypesUseCase,
                (List<ProductType> productTypes) ->
                        productTypes
                                .stream()
                                .map(ProductTypeMapper::toProductTypeDTO)
                                .collect(Collectors.toList())
        );
    }

    @Override
    public CompletionStage<ResponseEntity> deleteProductType(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                deleteProductTypeUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional v) -> new ResponseEntity(HttpStatus.NO_CONTENT)
        );
    }
}
