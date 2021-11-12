package pedromachakio.com.github.services.impl;

import org.springframework.stereotype.Service;
import pedromachakio.com.github.domain.repository.OrdersDetailsDAO;
import pedromachakio.com.github.services.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private OrdersDetailsDAO ordersDetailsDAO;

    public OrderDetailsServiceImpl(OrdersDetailsDAO ordersDetailsDAO) {
        this.ordersDetailsDAO = ordersDetailsDAO;
    }
}
