package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.entity.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrdersDetailsDAO extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findByClient(Client client);

    @Query(" select p from OrderDetails p left join fetch p.items where p.id = :id ")
    Optional<OrderDetails> findFetchId(@Param("id") Integer id);

}
