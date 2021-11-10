package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.Client;

import java.util.List;

public interface ClientsDAO extends JpaRepository<Client, Integer> {


    List<Client> findByNameLike(String name);
}
