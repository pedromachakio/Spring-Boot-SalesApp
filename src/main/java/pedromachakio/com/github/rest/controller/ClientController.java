package pedromachakio.com.github.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.repository.ClientsDAO;

import java.util.Optional;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    private ClientsDAO clientsDAO;

    public ClientController(ClientsDAO clientsDAO) { // dependency injection through constructor
        this.clientsDAO = clientsDAO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) { // para dizer que esta var vai estar no actual link/path
        Optional<Client> clientOptional = clientsDAO.findById(id); // optional porque pode ou não existir um cliente com este id

        if (clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.get()); // para responder com 200 e com o respetivo cliente desse id
        } else {
            return ResponseEntity.notFound().build(); //404
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody Client client) { // request porque é o utilizador que fornece
        Client savedClient = clientsDAO.save(client);
        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
        Optional<Client> clientOptional = clientsDAO.findById(id);

        if (clientOptional.isPresent()) {
            clientsDAO.delete(clientOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); //404
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateClient(@PathVariable Integer id, @RequestBody Client providedClient) {

        return clientsDAO
                .findById(id)
                .map(alreadyExistingClient -> { // if is present does this, otherwise return an empty Optional (check this map method)
                    providedClient.setId(alreadyExistingClient.getId());
                    //client.setName(existingClient.getName()); só é preciso fazer para o ID o resto das props atualizados já estão no RequestBody e são feitas tmb
                    clientsDAO.save(providedClient);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build()
                );
    }
}
