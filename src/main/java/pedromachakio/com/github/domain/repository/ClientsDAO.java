package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pedromachakio.com.github.domain.entity.Client;

import java.util.List;

public interface ClientsDAO extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name); // has to follow convenção dos query methods e fica ready behind the scenes

    @Query(value = " select c from Client c where c.name like :name ")
    List<Client> findClientWithPartiallyMatchingName(@Param("name") String name);

    boolean existsByName(String name);

    @Modifying
    void deleteByName(String name);

    @Query(" select c from Client c left join fetch c.orderDetails where c.id = :id ")
    Client findClientFetchOrders(@Param("id") Integer id);


}
