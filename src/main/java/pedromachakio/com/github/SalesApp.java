package pedromachakio.com.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.entity.OrderDetails;
import pedromachakio.com.github.domain.repository.ClientsDAO;
import pedromachakio.com.github.domain.repository.OrdersDetailsDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SalesApp {

    @Bean
    public CommandLineRunner init(@Autowired ClientsDAO clientsDAO, @Autowired OrdersDetailsDAO ordersDetailsDAO) {
        return args -> {
            System.out.println("--- Saving Clients ---");

            Client client_BeThereHealth = new Client("BeThereHealth");
            clientsDAO.save(client_BeThereHealth);

            OrderDetails order = new OrderDetails();
            order.setClient_OrderDetails(client_BeThereHealth);
            order.setOrderDate(LocalDate.now());
            order.setTotalPrice(BigDecimal.valueOf(100));

            ordersDetailsDAO.save(order);

            Client client = clientsDAO.findClientFetchOrders(client_BeThereHealth.getId());
            System.out.println(client);
            System.out.println(client.getOrderDetails());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
