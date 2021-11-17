package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedromachakio.com.github.domain.entity.User;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
