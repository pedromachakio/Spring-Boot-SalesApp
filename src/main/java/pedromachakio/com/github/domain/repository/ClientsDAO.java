package pedromachakio.com.github.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pedromachakio.com.github.domain.entity.Client;

import java.util.List;

@Repository
public class ClientsDAO {

    private static final String SQL_INSERT = "INSERT INTO CLIENT (NAME) VALUES (?) ";
    private static final String SQL_SELECT_ALL = "SELECT * FROM CLIENT ";
    private static final String SQL_SELECT_SPECIFIC_CLIENT = "SELECT * FROM CLIENT WHERE NAME LIKE ? ";
    private static final String SQL_UPDATE = "UPDATE CLIENT SET NAME = ? WHERE ID = ? ";
    private static final String SQL_DELETE = "DELETE FROM CLIENT WHERE ID = ? ";


    @Autowired // para o Spring injetar uma instância de jdbcTemplate para poder usá-lo
    JdbcTemplate jdbcTemplate;


    public Client save(Client client) {
        jdbcTemplate.update(SQL_INSERT, client.getName()); // recebe scripts de sql nativo // ele fez assim new Object[]{client.getName()}
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(SQL_UPDATE, client.getName(), client.getId()); // removed explicit array creation as above
        return client;
    }

    public void delete(Client client) {
        delete(client.getId()); // assim os dois métodos que fazem a mesma coisa convergem só num independentemente de o utilizador fornecer um Cliente ou apenas um Id
    }

    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    public List<Client> getClientByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_SPECIFIC_CLIENT, new Object[]{"%" + name + "%"}, getRowMapper());
    }

    public List<Client> displayClientsList() {
        return jdbcTemplate.query(SQL_SELECT_ALL, getRowMapper());
    }

    private RowMapper<Client> getRowMapper() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("ID");
            String nome = resultSet.getString("NAME");
            return new Client(id, nome);
        };
    }


}
