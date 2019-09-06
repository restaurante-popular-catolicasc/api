package br.org.restaurantepopular.data_db.repository.category;

import br.org.restaurantepopular.data_db.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<CategoryEntity, String> { }
