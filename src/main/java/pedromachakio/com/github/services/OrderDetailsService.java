package pedromachakio.com.github.services;

import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.rest.controller.dto.OrderDetailsDTO;

public interface OrderDetailsService {

    OrderDetails saveOrder (OrderDetailsDTO orderDetailsDTO);
}
