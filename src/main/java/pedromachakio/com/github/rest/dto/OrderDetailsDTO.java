package pedromachakio.com.github.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pedromachakio.com.github.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer client;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal totalPrice;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ProductsOrderedDTO> items;


}
