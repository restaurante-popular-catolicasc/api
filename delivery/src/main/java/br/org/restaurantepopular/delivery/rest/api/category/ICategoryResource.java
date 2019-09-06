package br.org.restaurantepopular.delivery.rest.api.category;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/categories")
public interface ICategoryResource {

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<CategoryDTO> getCategoryById(@PathVariable("id") String id);

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ResponseEntity> createCategory(@RequestBody CategoryDTO categoryDTO);

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<ResponseEntity> updateCategory(@RequestBody CategoryDTO categoryDTO);

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    CompletionStage<List<CategoryDTO>> listCategories();

    @DeleteMapping(value = "/{id}")
    CompletionStage<ResponseEntity> deleteCategory(@PathVariable("id") String id);
}
