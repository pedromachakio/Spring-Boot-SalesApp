package pedromachakio.com.github.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/*@Getter // lombok gera automaticamente getter ja incluido no @Data
@Setter // lombok gera automaticamente setter*/
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;


}
