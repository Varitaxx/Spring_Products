package eu.asgardschmiede.sproducts.repository;

import eu.asgardschmiede.sproducts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
