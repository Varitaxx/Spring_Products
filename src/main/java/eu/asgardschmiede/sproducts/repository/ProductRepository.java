package eu.asgardschmiede.sproducts.repository;

import eu.asgardschmiede.sproducts.model.Category;
import eu.asgardschmiede.sproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

}
