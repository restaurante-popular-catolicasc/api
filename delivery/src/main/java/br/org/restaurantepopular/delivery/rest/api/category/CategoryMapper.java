package br.org.restaurantepopular.delivery.rest.api.category;

import br.org.restaurantepopular.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toCategoryDTO(Category category) {
        var categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return categoryDTO;
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        return new Category.CategoryBuilder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

    }
}
