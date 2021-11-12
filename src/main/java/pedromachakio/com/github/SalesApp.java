package pedromachakio.com.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.repository.ClientsDAO;

@SpringBootApplication
public class SalesApp {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientsDAO clientsDAO) {
        return args -> {
            Client clientSergiy = new Client(null, "Sergiy");
            clientsDAO.save(clientSergiy);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
