package pedromachakio.com.github.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
// anotação opcional, desde que o nome da classe corresponda ao nome na tabela, which it should in the first place
public class Client {

    @Id // defines primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    //https://stackoverflow.com/questions/20603638/what-is-the-use-of-annotations-id-and-generatedvaluestrategy-generationtype
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 100)
    // anotação opcional, desde que o nome da classe corresponda ao nome na tabela, which it should in the first place
    private String name;

    // para poder obter todos os pedidos de um cliente, faz-se o mapeamento ao contrário também (dos pedidos para o cliente)
    // utiliza-se um Set porque cada pedido é único
    // um cliente para muitos pedidos
    @OneToMany(mappedBy = "client_OrderDetails") // no sql quem tem a chave é o pedido e não o cliente, no entanto se eu quiser ir buscar pedidos através dos clientes
    // é assim que se faz o mapeamento; tem que ser o nome com que foi instanciado no que tem a key, aka no OrderDetails
    private Set<OrderDetails> orderDetails;

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                " id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
