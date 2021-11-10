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

            System.out.println("--- Displaying List of Clients ---");
            List<Client> listOfClients = clientsDAO.displayClientsList();
            listOfClients.forEach(System.out::println);

            /*System.out.println("--- Updating Name Of Clients ---");
            listOfClients.forEach(client -> {
                client.setName(client.getName() + " ganda atualização");
                clientsDAO.update(client);
            });

            System.out.println("--- Getting Client by Specific Name, Including Partial Match ---");
            clientsDAO.getClientByName("Heal").forEach(System.out::println);


            //clientsDAO.displayClientsList().forEach(clientsDAO::delete);

            listOfClients = clientsDAO.displayClientsList(); // buscar novamente a lista de clientes para atualizar aqui com o que está no h2
             System.out.println("--- Displaying List of Clients After Delete ---");
            if (listOfClients.isEmpty()) {
                System.out.println("No clients left.");
            } else {
                listOfClients.forEach(System.out::println);
            }*/
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApp.class, args);

    }
}
