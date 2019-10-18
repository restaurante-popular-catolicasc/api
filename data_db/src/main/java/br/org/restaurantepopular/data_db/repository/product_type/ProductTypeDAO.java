package br.org.restaurantepopular.data_db.repository.product_type;

import br.org.restaurantepopular.data_db.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeDAO extends JpaRepository<ProductTypeEntity, String> { }
