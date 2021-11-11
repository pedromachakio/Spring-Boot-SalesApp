package pedromachakio.com.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pedromachakio.com.github.domain.entity.Client;

import java.util.List;

public interface ClientsDAO extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name); // has to follow convenção dos query methods e fica ready behind the scenes

    //se não for para seguir a convenção, pode-se usar qualquer nome desde que se use a annotation @Query e a query
    // assim pode-se fazer pesquisas mais complexas. P.s. dá para usar native SQL tmb, such as:
    //@Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    @Query(value = " select c from Client c where c.name like :name ")
    List<Client> findClientWithPartiallyMatchingName(@Param("name") String name);

    boolean existsByName(String name);

    @Modifying
        // os métodos que não são apenas de consulta e que modificam as tabelas têm de ser anotados com esta
    void deleteByName(String name);

    @Query(" select c from Client c left join fetch c.orderDetails where c.id = :id ")
        // left join traz clientes quer tenham pedidos quer não (se fosse só fetch so trazia os que têm pedidos)
    Client findClientFetchOrders(@Param("id") Integer id); // já que estou a usar lazy no Client, é assim que vou buscar o Set com os pedidos de cada cliente (por ID)


}
