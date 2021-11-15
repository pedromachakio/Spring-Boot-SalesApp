package pedromachakio.com.github.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.rest.controller.dto.OrderDetailsDTO;
import pedromachakio.com.github.services.OrderDetailsService;

@RestController
@RequestMapping("/api/orders")
public class OrderDetailsController {

    private OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveOrder(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = orderDetailsService.saveOrder(orderDetailsDTO);
        return orderDetails.getId();
    }

}
