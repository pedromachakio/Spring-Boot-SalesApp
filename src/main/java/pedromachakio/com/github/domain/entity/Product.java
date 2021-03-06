package pedromachakio.com.github.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/*@Getter // lombok gera automaticamente getter ja incluido no @Data
@Setter // lombok gera automaticamente setter*/
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String description;

    @Column(name = "UNIT_PRICE")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal unitPrice;


}
