package pedromachakio.com.github.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "{campo.nome.obrigatorio}") // mensagem de erro que mostra caso esteja vazio
    private String name;

    @Column(name = "nif", length = 9)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @Size(min = 9, max = 9, message = "{campo.cpf.invalido}")
    private String taxPayerId;

    @JsonIgnore // ignore in json response
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<OrderDetails> orderDetails;

}
