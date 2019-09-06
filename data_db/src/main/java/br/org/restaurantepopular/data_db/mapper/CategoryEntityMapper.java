package br.org.restaurantepopular.data_db.mapper;

import br.org.restaurantepopular.data_db.entity.CategoryEntity;
import br.org.restaurantepopular.entity.Category;

public class CategoryEntityMapper {

    public static CategoryEntity toCategoryEntity(Category category) {
        return new CategoryEntity.Builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category toCategory(CategoryEntity categoryEntity) {
        return new Category.CategoryBuilder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();

    }
}
