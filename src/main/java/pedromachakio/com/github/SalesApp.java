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

            //stem.out.println("--- Displaying List of Clients ---");
            //List<Client> listOfClients = clientsDAO.findAll();
            //listOfClients.forEach(System.out::println);
            boolean clientExists = clientsDAO.existsByName("Pedriem");
            System.out.println("Is there a client with the name Pedriem? " + clientExists);


            /*System.out.println("--- Updating Name Of Clients ---");
            listOfClients.forEach(client -> {
                client.setName(client.getName() + " ganda atualização");
                clientsDAO.save(client); // save do jpa repository tanto guarda como dá update, por isso é usado para ambos
            });


            System.out.println("--- Getting Client by Specific Name, Including Partial Match ---");
            clientsDAO.findByNameLike("Heal").forEach(System.out::println);


            clientsDAO.findAll().forEach(clientsDAO::delete);

            listOfClients = clientsDAO.findAll(); // buscar novamente a lista de clientes para atualizar aqui com o que está no h2
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
