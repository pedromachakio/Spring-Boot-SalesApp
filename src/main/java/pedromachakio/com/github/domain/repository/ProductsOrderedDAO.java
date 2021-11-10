package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.ProductOrdered;

public interface ProductsOrderedDAO extends JpaRepository<ProductOrdered, Integer> {
}
