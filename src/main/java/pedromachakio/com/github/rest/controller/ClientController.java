package pedromachakio.com.github.rest.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pedromachakio.com.github.domain.entity.Client;
import pedromachakio.com.github.domain.repository.ClientsDAO;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientsDAO clientsDAO;

    public ClientController(ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientsDAO
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find client."));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody Client client) {
        return clientsDAO.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer id) {
        clientsDAO
                .findById(id)
                .map(clientToBeDeleted -> {
                    clientsDAO.delete(clientToBeDeleted);
                    return clientToBeDeleted;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find client."));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable Integer id, @RequestBody Client providedClient) {

        clientsDAO
                .findById(id)
                .map(alreadyExistingClient -> {
                    providedClient.setId(alreadyExistingClient.getId());
                    clientsDAO.save(providedClient);
                    return alreadyExistingClient;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find client."));

    }

    @GetMapping
    public List<Client> findClient(Client filterClient) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Client> example = Example.of(filterClient, exampleMatcher);

        return clientsDAO.findAll(example);

    }
}
