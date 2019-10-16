package br.org.restaurantepopular.delivery.rest.api.product_type;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/productType")
public interface IProductTypeResource {

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ProductTypeDTO> getTypeById(@PathVariable("id") String id);

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ResponseEntity> createType(@RequestBody ProductTypeDTO productTypeDTO);

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ResponseEntity> updateType(@RequestBody ProductTypeDTO productTypeDTO);

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<List<ProductTypeDTO>> listTypes();

    @DeleteMapping(value = "/{id}")
    CompletionStage<ResponseEntity> deleteType(@PathVariable("id") String id);
}
