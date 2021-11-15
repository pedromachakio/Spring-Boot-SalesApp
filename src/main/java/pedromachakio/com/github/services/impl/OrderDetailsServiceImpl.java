package pedromachakio.com.github.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.domain.entity.Product;
import pedromachakio.com.github.domain.entity.ProductOrdered;
import pedromachakio.com.github.domain.repository.ClientsDAO;
import pedromachakio.com.github.domain.repository.OrdersDetailsDAO;
import pedromachakio.com.github.domain.repository.ProductsDAO;
import pedromachakio.com.github.domain.repository.ProductsOrderedDAO;
import pedromachakio.com.github.exception.BusinessLogicException;
import pedromachakio.com.github.rest.controller.dto.OrderDetailsDTO;
import pedromachakio.com.github.rest.controller.dto.ProductsOrderedDTO;
import pedromachakio.com.github.services.OrderDetailsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrdersDetailsDAO ordersDetailsDAO; // ao colocar final a anotação @RequiredArgsConstructor do lombok vai instanciá-los
    private final ClientsDAO clientsDAO;
    private final ProductsDAO productsDAO;
    private final ProductsOrderedDAO productsOrderedDAO;


    @Override
    @Transactional
    public OrderDetails saveOrder(OrderDetailsDTO orderDetailsDTO) {

        Integer clientId = orderDetailsDTO.getClient();
        BigDecimal totalPrice = orderDetailsDTO.getTotalPrice();

        Client clientWhoOrdered = clientsDAO
                .findById(clientId)
                .orElseThrow(() -> new BusinessLogicException("Invalid client ID bro."));


        OrderDetails orderToPopulate = new OrderDetails();
        orderToPopulate.setTotalPrice(totalPrice);
        orderToPopulate.setOrderDate(LocalDate.now());
        orderToPopulate.setClient(clientWhoOrdered);


        List<ProductOrdered> listOfOrderedProducts = convertItems(orderToPopulate, orderDetailsDTO.getItems());
        ordersDetailsDAO.save(orderToPopulate);
        productsOrderedDAO.saveAll(listOfOrderedProducts);
        orderToPopulate.setItems(listOfOrderedProducts);

        return orderToPopulate;
    }


    private List<ProductOrdered> convertItems(OrderDetails order, List<ProductsOrderedDTO> orderedItemsDto) {

        if (orderedItemsDto.isEmpty()) {
            throw new BusinessLogicException("An order has to have at least 1 item.");
        }

        return orderedItemsDto
                .stream()
                .map(item -> {
                    Integer productId = item.getProduct();
                    Product individualProduct = productsDAO
                            .findById(productId)
                            .orElseThrow(() -> new BusinessLogicException("Invalid product ID bro: " + productId));


                    ProductOrdered productOrdered = new ProductOrdered();
                    productOrdered.setQuantity(item.getQuantity());
                    productOrdered.setOrderDetails_ProductOrdered(order);
                    productOrdered.setProduct(individualProduct);

                    return productOrdered;
                }).collect(Collectors.toList());
    }
}
