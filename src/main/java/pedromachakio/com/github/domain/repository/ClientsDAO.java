package pedromachakio.com.github.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pedromachakio.com.github.domain.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientsDAO {

    //private static final String SQL_INSERT = "INSERT INTO CLIENT (NAME) VALUES (?) ";
    //private static final String SQL_SELECT_ALL = "SELECT * FROM CLIENT ";
    //private static final String SQL_SELECT_SPECIFIC_CLIENT = "SELECT * FROM CLIENT WHERE NAME LIKE ? ";
    //private static final String SQL_UPDATE = "UPDATE CLIENT SET NAME = ? WHERE ID = ? ";
    //private static final String SQL_DELETE = "DELETE FROM CLIENT WHERE ID = ? ";


    /*@Autowired // para o Spring injetar uma instância de jdbcTemplate para poder usá-lo
    JdbcTemplate jdbcTemplate;*/

    @Autowired
    EntityManager entityManager; // responsible for all operations with the mapped entities

    @Transactional // required with entityManager
    public Client save(Client client) {
        //jdbcTemplate.update(SQL_INSERT, client.getName()); // recebe scripts de sql nativo // ele fez assim new Object[]{client.getName()}
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Client update(Client client) {
        //jdbcTemplate.update(SQL_UPDATE, client.getName(), client.getId()); // removed explicit array creation as above
        entityManager.merge(client); // sincroniza este cliente com o cliente que foi anteriormente salvo no entity manager
        return client;
    }

    @Transactional
    public void delete(Client client) {
        //delete(client.getId()); // assim os dois métodos que fazem a mesma coisa convergem só num independentemente de o utilizador fornecer um Cliente ou apenas um Id
        if (!entityManager.contains(client)) {
            client = entityManager.merge(client);
        }
        entityManager.remove(client);
    }

    @Transactional
    public void delete(Integer id) {
        //jdbcTemplate.update(SQL_DELETE, id);

        Client clientToBeDeleted = entityManager.find(Client.class, id);
        delete(clientToBeDeleted);

        // podia simplificar as such delete(entityManager.find(Client.class, id));
    }

    @Transactional(readOnly = true)
    public List<Client> getClientByName(String name) {
        //return jdbcTemplate.query(SQL_SELECT_SPECIFIC_CLIENT, new Object[]{"%" + name + "%"}, getRowMapper());
        String jpql = " select c from Client c where c.name like :name "; // os dois pontos indicam que é um parâmetro que vai ser passado
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Client> displayClientsList() {
        //return jdbcTemplate.query(SQL_SELECT_ALL, getRowMapper());
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }

    /*private RowMapper<Client> getRowMapper() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("ID");
            String nome = resultSet.getString("NAME");
            return new Client(id, nome);
        };
    }*/


}
