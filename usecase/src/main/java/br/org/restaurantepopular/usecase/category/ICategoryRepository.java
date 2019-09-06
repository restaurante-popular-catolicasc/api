package br.org.restaurantepopular.usecase.category;

import br.org.restaurantepopular.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

    void save(Category category);

    void update(Category category);

    List<Category> listCategories();

    Optional<Category> findById(String id);

    void delete(String id);
}
