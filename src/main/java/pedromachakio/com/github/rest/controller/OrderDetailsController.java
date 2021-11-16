package pedromachakio.com.github.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.domain.entity.ProductOrdered;
import pedromachakio.com.github.domain.enums.OrderStatus;
import pedromachakio.com.github.rest.dto.OrderDetailsDTO;
import pedromachakio.com.github.rest.dto.OrderInfoDTO;
import pedromachakio.com.github.rest.dto.OrderedProductInfoDto;
import pedromachakio.com.github.rest.dto.StatusUpdateDTO;
import pedromachakio.com.github.services.OrderDetailsService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderDetailsController {

    private OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer saveOrder(@RequestBody @Valid OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = orderDetailsService.saveOrder(orderDetailsDTO);
        return orderDetails.getId();
    }

    @GetMapping("/{id}")
    public OrderInfoDTO getById(@PathVariable Integer id) {
        return orderDetailsService
                .getCompleteRequest(id)
                .map(order -> convertOrder(order))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find client with given id: " + id));

    }

    @PatchMapping("{id}")
    // se fosse o Put tinha que se passar a info TODA do pedido, nao se pode atualizar apenas um campo otherwise dá erro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody @Valid StatusUpdateDTO statusUpdateDTO) {
        String newStatus = statusUpdateDTO.getNewStatus();
        orderDetailsService.statusUpdate(id, OrderStatus.valueOf(newStatus));
    }

    private OrderInfoDTO convertOrder(OrderDetails order) {
        return OrderInfoDTO
                .builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .taxPayerId(order.getClient().getTaxPayerId())
                .clientName(order.getClient().getName())
                .totalAmount(order.getTotalPrice())
                .status(order.getStatus().name())
                .items(convertOrderedItem(order.getItems()))
                .build();
    }

    private List<OrderedProductInfoDto> convertOrderedItem(List<ProductOrdered> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items
                .stream()
                .map(singleItem ->
                        OrderedProductInfoDto
                                .builder()
                                .description(singleItem.getProduct().getDescription())
                                .quantity(singleItem.getQuantity())
                                .unitPrice(singleItem.getProduct().getUnitPrice())
                                .build()
                ).collect(Collectors.toList());
    }

}
