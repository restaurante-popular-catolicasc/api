package br.org.restaurantepopular.data_db.repository.category;

import br.org.restaurantepopular.data_db.entity.CategoryEntity;
import br.org.restaurantepopular.data_db.mapper.CategoryEntityMapper;
import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.ICategoryRepository;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryRepository implements ICategoryRepository {

    private final CategoryDAO categoryDAO;

    public CategoryRepository(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Optional<Category> save(Category category) {
        CategoryEntity entity = categoryDAO.save(CategoryEntityMapper.toCategoryEntity(category));
        return Optional.of(CategoryEntityMapper.toCategory(entity));
    }

    @Override
    public void update(Category category) throws NotFoundException {
        if (!categoryDAO.existsById(category.getId()))
            throw new NotFoundException(String.format("Not found a category with this %s ID", category.getId()));

        categoryDAO.save(CategoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public List<Category> listCategories() {
        return categoryDAO
                .findAll()
                .stream()
                .map(CategoryEntityMapper::toCategory)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryDAO
                .findById(id)
                .map(CategoryEntityMapper::toCategory);
    }

    @Override
    public void delete(String id) {
        if (!categoryDAO.existsById(id))
            throw new NotFoundException(String.format("Not found a category with this %s ID", id));

        categoryDAO.deleteById(id);
    }
}
