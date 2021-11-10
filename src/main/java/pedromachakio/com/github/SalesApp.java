package pedromachakio.com.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.repository.Clients;

import java.util.List;

@SpringBootApplication
public class SalesApp {

    @Bean
    public CommandLineRunner init(@Autowired Clients clients) {
        return args -> {
            Client testClient = new Client();
            testClient.setName("Pedriem");
            clients.saveClient(testClient);

            Client testClient2 = new Client();
            testClient2.setName("BeThereHealth");
            clients.saveClient(testClient2);

            List<Client> clientsList = clients.displayClientsList();
            clientsList.forEach(client -> {System.out.println(client);});
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
