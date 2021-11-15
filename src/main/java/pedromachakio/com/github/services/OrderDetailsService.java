package pedromachakio.com.github.services;

import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.domain.enums.OrderStatus;
import pedromachakio.com.github.rest.dto.OrderDetailsDTO;

import java.util.Optional;

public interface OrderDetailsService {

    OrderDetails saveOrder (OrderDetailsDTO orderDetailsDTO);

    Optional<OrderDetails> getCompleteRequest(Integer id);

    void statusUpdate(Integer id, OrderStatus orderStatus);

}
