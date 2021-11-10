package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.OrderDetails;

public interface OrdersDetailsDAO extends JpaRepository<OrderDetails, Integer> {
}
