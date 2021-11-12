package pedromachakio.com.github.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "nif", length = 9)
    private String taxPayerId;

    @JsonIgnore // ignore in json response
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<OrderDetails> orderDetails;

}
