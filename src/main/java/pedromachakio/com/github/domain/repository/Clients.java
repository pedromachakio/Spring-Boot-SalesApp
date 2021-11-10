package pedromachakio.com.github.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pedromachakio.com.github.domain.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients {

    private static final String SQL_INSERT = "INSERT INTO CLIENT (NAME) VALUES (?) ";
    private static final String SQL_SELECT_ALL = "SELECT * FROM CLIENT";

    @Autowired // para o Spring injetar uma instância de jdbcTemplate para poder usá-lo
    JdbcTemplate jdbcTemplate;


    public Client saveClient(Client client) {
        jdbcTemplate.update(SQL_INSERT, client.getName()); // recebe scripts de sql nativo // ele fez assim new Object[]{client.getName()}
        return client;
    }

    public List<Client> displayClientsList() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                String clientName = resultSet.getString("NAME");
                Integer clientId = resultSet.getInt("ID");
                return new Client(clientId, clientName);
            }
        });
    }


}
