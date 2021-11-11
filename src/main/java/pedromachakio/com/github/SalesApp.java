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

@SpringBootApplication
public class SalesApp {

    @Bean
    public CommandLineRunner init(@Autowired ClientsDAO clientsDAO, @Autowired OrdersDetailsDAO ordersDetailsDAO) {
        return args -> {
            System.out.println("--- Saving Clients ---");

            Client client_BeThereHealth = new Client("BeThereHealth");
            clientsDAO.save(client_BeThereHealth);

            OrderDetails order = new OrderDetails();
            order.setClient(client_BeThereHealth);
            order.setOrderDate(LocalDate.now());
            order.setTotalPrice(BigDecimal.valueOf(100));

            ordersDetailsDAO.save(order);

            /*Client client = clientsDAO.findClientFetchOrders(client_BeThereHealth.getId());
            System.out.println(client);
            System.out.println(client.getOrderDetails());*/

           ordersDetailsDAO.findByClient(client_BeThereHealth).forEach(System.out::println); // find os pedidos deste cliente
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
