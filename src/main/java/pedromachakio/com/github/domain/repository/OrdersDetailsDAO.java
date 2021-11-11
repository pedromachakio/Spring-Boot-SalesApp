package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.entity.OrderDetails;

import java.util.List;

public interface OrdersDetailsDAO extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findByClient(Client client);

}
