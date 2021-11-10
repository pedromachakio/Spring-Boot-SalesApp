package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.Product;

public interface ProductsDAO extends JpaRepository<Product, Integer> {
}
