package pedromachakio.com.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.repository.ClientsDAO;

import java.util.List;

@SpringBootApplication
public class SalesApp {

    @Bean
    public CommandLineRunner init(@Autowired ClientsDAO clientsDAO) {
        return args -> {
            System.out.println("--- Saving Clients ---");
            clientsDAO.save(new Client("Pedriem"));
            clientsDAO.save(new Client("BeThereHealth"));

           List<Client> clientsWithMatchingName = clientsDAO.findClientWithPartiallyMatchingName("Heal");
           clientsWithMatchingName.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
